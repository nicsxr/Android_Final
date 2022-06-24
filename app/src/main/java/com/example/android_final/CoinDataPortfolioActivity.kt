package com.example.android_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_final.api.AverageCoinAdapter
import com.example.android_final.models.AverageCoins
import com.example.android_final.models.api.CoinsItem
import com.squareup.picasso.Picasso
import kotlin.math.round

class CoinDataPortfolioActivity : AppCompatActivity() {
    private lateinit var coin : AverageCoins
    private lateinit var db : AppDatabase
    lateinit var resourcesAdapter: AverageCoinAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_data_portfolio)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.portfolioRecyclerView)
        recyclerView.layoutManager = linearLayoutManager

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "UserCoins"
        ).build()


        coin = intent.getSerializableExtra("coin") as AverageCoins
        findViewById<TextView>(R.id.portfolioTitle).text = coin.name
        findViewById<TextView>(R.id.textTotalBuy).text = "$"+(round(coin.totalBuy * 100) / 100)
        findViewById<TextView>(R.id.averageBuyPrice).text = "$"+(round(coin.averagePrice * 100) / 100)
        findViewById<TextView>(R.id.textTotalCoins).text = (round(coin.coinAmount * 100) / 100).toString() + " " +coin.symbol.toUpperCase()

        Picasso.get().load(coin.image).into(findViewById<ImageView>(R.id.portfolioImage))

        getData()
    }

    private fun getData() {
        Thread{
            val data = db.userCoinsDao().getCoinData(coin.coinId)
            runOnUiThread {
                resourcesAdapter = AverageCoinAdapter(this, data)
                resourcesAdapter.notifyDataSetChanged()
                recyclerView.adapter = resourcesAdapter
            }

        }.start()
    }
}