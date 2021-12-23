package com.example.eus.Cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class],version = 1,exportSchema = false)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO

    companion object {
        private var INSTANCE : CacheDatabase? = null

        fun getInstance(context: Context) : CacheDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    CacheDatabase::class.java,
                    context.packageName)
                    .build()
            }

            return INSTANCE as CacheDatabase
        }
    }
}