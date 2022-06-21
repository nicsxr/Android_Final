package com.example.android_final.api

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.android_final.models.api.Coins
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.R
import com.squareup.picasso.Picasso


class CoinsAdapter (val context: Context, val coins: Coins) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>(){
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
        holder.price.text = String.format("%.2f", coins[position].current_price)
        holder.change_24h.text = String.format("%.2f",coins[position].price_change_24h)
        if (coins[position].price_change_24h >= 0){
            holder.change_24h.setTextColor(Color.parseColor("#00FF00"))
            holder.change_24h.text = "+" + holder.change_24h.text + "%"
        }else{
            holder.change_24h.setTextColor(Color.parseColor("#FF0000"))
            holder.change_24h.text = holder.change_24h.text as String + "%"
        }
        Picasso.get().load(coins[position].image).into(holder.icon);
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}