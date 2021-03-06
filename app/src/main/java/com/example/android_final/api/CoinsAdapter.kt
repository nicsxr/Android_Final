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
import com.example.android_final.CoinInfoActivity
import com.example.android_final.MainActivity


class CoinsAdapter (val context: Context, val coins: Coins) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>(){
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon : ImageView = itemView.findViewById(R.id.coinIcon)
        var name : TextView = itemView.findViewById(R.id.coinName)
        var symbol : TextView = itemView.findViewById(R.id.coinSymbol)
        var price : TextView = itemView.findViewById(R.id.coinPrice)
        var change_24h : TextView = itemView.findViewById(R.id.coinChange24)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.coin_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = coins[position].name
        holder.symbol.text = coins[position].symbol.toUpperCase()
        holder.price.text = String.format("%.2f", coins[position].current_price)
        holder.change_24h.text = String.format("%.2f",coins[position].price_change_percentage_24h)
        if (coins[position].price_change_percentage_24h >= 0){
            holder.change_24h.setTextColor(Color.parseColor("#00FF00"))
            holder.change_24h.text = "+" + holder.change_24h.text + "%"
        }else{
            holder.change_24h.setTextColor(Color.parseColor("#FF0000"))
            holder.change_24h.text = holder.change_24h.text as String + "%"
        }
        Picasso.get().load(coins[position].image).into(holder.icon)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CoinInfoActivity::class.java)
            intent.putExtra("coin", coins[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}