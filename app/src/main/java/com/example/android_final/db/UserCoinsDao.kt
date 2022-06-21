package com.example.android_final.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_final.models.DB.UserCoins

@Dao
interface UserCoinsDao {
    @Query("Select * from UserCoins")
    fun getAllData() : List<UserCoins>

    @Query("Select * from UserCoins WHERE coinId LIKE :coinId")
    fun getCoinData(coinId : String) : List<UserCoins>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData()

//    @Query("Select coinId, SUM(buyAmount) as totalBuy, AVG(buyPrice), MAX(buyDate), imageUrl from UserCoins")
//    fun getAverageData() : List<UserCoins>

}