package com.lebahakatsuki.menuapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lebahakatsuki.menuapp.data.model.OrderEntity

@Database(entities = [OrderEntity::class], version = 1)
abstract class OrderDatabase: RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getDatabase(context: Context): OrderDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OrderDatabase::class.java,
                        "orderDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}