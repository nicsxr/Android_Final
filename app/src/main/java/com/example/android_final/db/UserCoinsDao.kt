package com.example.android_final.db

import androidx.room.*
import com.example.android_final.models.DB.UserCoins
@Dao
interface UserCoinsDao {
    @Query("Select * from UserCoins")
    fun getAllData() : List<UserCoins>

    @Query("Select * from UserCoins WHERE coinId LIKE :coinId")
    fun getCoinData(coinId : String) : List<UserCoins>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(userCoins: UserCoins)

    @Query("DELETE FROM UserCoins")
    fun deleteData()

//    @Query("Select coinId, SUM(buyAmount) as totalBuy, AVG(buyPrice), MAX(buyDate), imageUrl from UserCoins")
//    fun getAverageData() : List<UserCoins>

}