package com.example.android_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import com.example.android_final.models.DB.UserCoins
import com.example.android_final.models.api.CoinsItem
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.math.round

class CoinInfoActivity : AppCompatActivity() {
    private lateinit var coin : CoinsItem
    private lateinit var buyButton : Button
    private lateinit var buyAmountInput : EditText
    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_info)
        buyButton = findViewById(R.id.buyButton)
        buyAmountInput = findViewById(R.id.buyAmountInput)
        coin = intent.getSerializableExtra("coin") as CoinsItem
        findViewById<TextView>(R.id.titleTextView).text = coin.name
        Picasso.get().load(coin.image).into(findViewById<ImageView>(R.id.titleImageView))


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "UserCoins"
        ).build()

        buyButton.setOnClickListener {
            buyCrypto()
        }
    }

    private fun buyCrypto() {
        val amount = round(buyAmountInput.text.toString().toDouble()  * 100) / 100
        val dateNow = System.currentTimeMillis()


        Thread{
            val userCoin = UserCoins(coin.id, coin.name, coin.symbol, amount, amount/coin.current_price, coin.current_price, dateNow, coin.image)
            db.userCoinsDao().insertData(userCoin)
        }.start()
        Toast.makeText(this,"Bought $" + amount + " worth of " + coin.name + " at $" + coin.current_price, Toast.LENGTH_LONG).show()
        finish()
        println(amount)
    }
}