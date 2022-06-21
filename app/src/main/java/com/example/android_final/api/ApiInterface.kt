package com.example.android_final.api

import retrofit2.Call
import com.example.android_final.models.api.Coins
import retrofit2.http.GET

interface ApiInterface {
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    fun getCoins() : Call<Coins>
}