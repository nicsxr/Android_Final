package com.example.android_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.models.api.Coins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://api.coingecko.com/api/v3/"

    lateinit var resourcesAdapter: CoinsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCoins(this)
    }


    private fun getCoins(context: Context){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(ApiInterface::class.java)

        val data = retrofitBuilder.getCoins()

        data.enqueue(object : Callback<Coins?> {
            override fun onResponse(
                call: Call<Coins?>,
                response: Response<Coins?>
            ) {
                val body = response.body()!!
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView = findViewById(R.id.coinsRecyclerView)
                recyclerView.layoutManager = linearLayoutManager
                resourcesAdapter = CoinsAdapter(baseContext, body)
                resourcesAdapter.notifyDataSetChanged()
                recyclerView.adapter = resourcesAdapter
                println(body)
            }

            override fun onFailure(call: Call<Coins?>, t: Throwable) {
                println(t)
                println("------------------------------------------")
            }
        })
    }
}