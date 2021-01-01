package com.welniak.hotdogmeter

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

internal class HotDogImageAnalyzer(
    private val hotDogScoreListener: (Float) -> Unit,
    private val hotDogClassifier: HotDogClassifier
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        hotDogScoreListener(
            hotDogClassifier.getHotDogScore(imageProxy.toTensorImage())
        )
        imageProxy.close()
    }
}
