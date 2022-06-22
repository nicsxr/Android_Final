package com.example.android_final.models.DB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "UserCoins")
data class UserCoins(
    val coinId : String,
    val buyAmount : Double,
    val buyPrice : Double,
    val buyDate : Long,
    val imageUrl : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null
}