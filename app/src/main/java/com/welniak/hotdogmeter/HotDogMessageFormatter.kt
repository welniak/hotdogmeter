package com.welniak.hotdogmeter

fun Float.toHotDogProbabilityMessage() = "${String.format("%.2f", this * 100)} % hot dog"
