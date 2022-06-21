package com.example.android_final

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_final.db.UserCoinsDao
import com.example.android_final.models.DB.UserCoins

@Database(entities = [UserCoins::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userCoinsDao(): UserCoinsDao
}