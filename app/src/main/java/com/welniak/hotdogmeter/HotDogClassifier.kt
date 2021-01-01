package com.welniak.hotdogmeter

import android.content.Context
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

internal class HotDogClassifier(context: Context) {

    private val classifierOptions = ImageClassifier.ImageClassifierOptions
        .builder()
        .setScoreThreshold(SCORE_THRESHOLD)
        .build()

    private val imageClassifier by lazy {
        ImageClassifier.createFromFileAndOptions(context, MODEL_PATH, classifierOptions)
    }

    fun getHotDogScore(image: TensorImage): Float {
        var hotDogScore = 0.0f
            imageClassifier.classify(image)
            .forEach { classifications ->
                hotDogScore = classifications.toHotDogScore()
            }
        return hotDogScore
    }

    private fun Classifications.toHotDogScore(): Float {
        categories.forEach { category ->
            if (category.label == HOT_DOG_LABEL)
                return category.score
        }
        return 0.0f
    }

    private companion object {
        const val MODEL_PATH = "mobilenet_v1_1.0_224_quant.tflite"
        const val HOT_DOG_LABEL = "hotdog"
        const val SCORE_THRESHOLD = 0.05f
    }
}
