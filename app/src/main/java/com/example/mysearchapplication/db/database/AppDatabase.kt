package com.example.mysearchapplication.db.database

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysearchapplication.db.entity.PhotoData
import com.example.mysearchapplication.db.dao.UserSearchDao

@Database(entities = [PhotoData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userSearchDao(): UserSearchDao

    companion object {
        private var sInstance: AppDatabase? = null

        @VisibleForTesting
        val DATABASE_NAME = "basic-sample-db"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return sInstance as AppDatabase
        }
    }

}