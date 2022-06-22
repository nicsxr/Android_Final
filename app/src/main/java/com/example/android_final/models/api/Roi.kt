package com.example.android_final.models.api

import java.io.Serializable

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
) : Serializable