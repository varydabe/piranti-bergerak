package com.lebahakatsuki.menuapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lebahakatsuki.menuapp.data.model.DrinkEntity

//Drink Database
@Database(entities = [DrinkEntity::class], version = 1)
abstract class DrinkDatabase: RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object{
        @Volatile
        private var INSTANCE: DrinkDatabase? = null

        fun getDatabase(context: Context): DrinkDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrinkDatabase::class.java,
                    "drinkDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}