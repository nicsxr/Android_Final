package com.example.android_final.api

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.android_final.models.api.Coins
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.R
import com.squareup.picasso.Picasso
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.android_final.CoinDataPortfolioActivity
import com.example.android_final.CoinInfoActivity
import com.example.android_final.MainActivity
import com.example.android_final.models.AverageCoins


class AverageCoinAdapter (val context: Context, val coins: List<AverageCoins>) : RecyclerView.Adapter<AverageCoinAdapter.ViewHolder>(){
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon : ImageView
        var name : TextView
        var symbol : TextView
        var price : TextView
        var change_24h : TextView

        init{
            icon = itemView.findViewById(R.id.coinIcon)
            name = itemView.findViewById(R.id.coinName)
            symbol = itemView.findViewById(R.id.coinSymbol)
            price = itemView.findViewById(R.id.coinPrice)
            change_24h = itemView.findViewById(R.id.coinChange24)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.coin_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = coins[position].name
        holder.symbol.text = coins[position].symbol.toUpperCase()
        holder.price.text = String.format("%.2f", coins[position].totalBuy)
        holder.change_24h.text = String.format("%.2f", coins[position].coinAmount)
        Picasso.get().load(coins[position].image).into(holder.icon)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CoinDataPortfolioActivity::class.java)
            intent.putExtra("coin", coins[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}