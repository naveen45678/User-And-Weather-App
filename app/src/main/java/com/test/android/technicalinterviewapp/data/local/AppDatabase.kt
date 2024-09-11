package com.test.android.technicalinterviewapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.android.technicalinterviewapp.data.local.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
     abstract fun userDao():UserDao


    companion object {
        const val DATABASE_NAME = "weather_app_db"

    }
}