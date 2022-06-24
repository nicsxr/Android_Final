package com.example.android_final.db

import androidx.room.*
import com.example.android_final.models.AverageCoins
import com.example.android_final.models.DB.UserCoins
@Dao
interface UserCoinsDao {
    @Query("Select * from UserCoins")
    fun getAllData() : MutableList<UserCoins>

    @Query("Select coinId, name, symbol, buyAmount as totalBuy, coinAmount, buyPrice as averagePrice, buyDate, imageUrl as image from UserCoins WHERE coinId LIKE :coinId ORDER BY buyDate desc")
    fun getCoinData(coinId : String) : MutableList<AverageCoins>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(userCoins: UserCoins)

    @Query("DELETE FROM UserCoins")
    fun deleteData()

    @Query("Select coinId, name, symbol, SUM(buyAmount) as totalBuy, SUM(coinAmount) as coinAmount, AVG(buyPrice) as averagePrice, MAX(buyDate), imageUrl as image from UserCoins Group by coinId ORDER BY totalBuy desc")
    fun getAverageData() : List<AverageCoins>
}