package com.lebahakatsuki.menuapp.data.di

import android.app.Application
import androidx.room.Room
import com.lebahakatsuki.menuapp.BuildConfig
import com.lebahakatsuki.menuapp.data.database.DrinkDao
import com.lebahakatsuki.menuapp.data.database.DrinkDatabase
import com.lebahakatsuki.menuapp.data.database.FoodDatabase
import com.lebahakatsuki.menuapp.data.database.OrderDatabase
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.network.RetrofitClient
import com.lebahakatsuki.menuapp.data.repository.DrinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Create provide Database for Drink, Food, and Order
    // Singleton => only created this one instance in the whole app.
    @Provides
    @Singleton
    fun provideDrinkDatabase(
        app: Application
    ) = Room.databaseBuilder(app, DrinkDatabase::class.java, "drink_database")
            .build()

    @Provides
    fun provideDrinkDao(db: DrinkDatabase) = db.drinkDao()

    @Provides
    @Singleton
    fun provideFoodDatabase(
        app: Application
    ) = Room.databaseBuilder(app, FoodDatabase::class.java, "food_database")
        .build()

    @Provides
    fun provideFoodDao(db: FoodDatabase) = db.foodDao()

    @Provides
    @Singleton
    fun provideOrderDatabase(
        app: Application
    ) = Room.databaseBuilder(app, OrderDatabase::class.java, "order_database")
        .build()

    @Provides
    fun provideOrderDao(db: OrderDatabase) = db.orderDao()
}