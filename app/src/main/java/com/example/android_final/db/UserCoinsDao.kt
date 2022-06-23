package com.example.android_final.db

import androidx.room.*
import com.example.android_final.models.AverageCoins
import com.example.android_final.models.DB.UserCoins
@Dao
interface UserCoinsDao {
    @Query("Select * from UserCoins")
    fun getAllData() : MutableList<UserCoins>

    @Query("Select * from UserCoins WHERE coinId LIKE :coinId")
    fun getCoinData(coinId : String) : MutableList<UserCoins>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(userCoins: UserCoins)

    @Query("DELETE FROM UserCoins")
    fun deleteData()

    @Query("Select coinId, name, symbol, SUM(buyAmount) as totalBuy, SUM(coinAmount) as coinAmount, AVG(buyPrice) as averagePrice, MAX(buyDate), imageUrl as image from UserCoins Group by coinId")
    fun getAverageData() : List<AverageCoins>

}