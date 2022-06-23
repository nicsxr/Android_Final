package com.example.android_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android_final.models.AverageCoins
import com.example.android_final.models.api.CoinsItem

class CoinDataPortfolioActivity : AppCompatActivity() {
    private lateinit var coin : AverageCoins
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_data_portfolio)

        coin = intent.getSerializableExtra("coin") as AverageCoins
        findViewById<TextView>(R.id.testTextView).text = coin.name
    }
}