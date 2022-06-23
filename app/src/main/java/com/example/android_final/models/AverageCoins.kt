package com.example.android_final.models

import java.io.Serializable

class AverageCoins(
    val coinId : String,
    val name : String,
    val symbol : String,
    var totalBuy : Double,
    var coinAmount : Double,
    var averagePrice : Double,
    val image : String
) : Serializable
