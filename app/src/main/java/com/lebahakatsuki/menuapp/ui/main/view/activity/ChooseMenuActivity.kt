package com.lebahakatsuki.menuapp.ui.main.view.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.OrderEntity
import com.lebahakatsuki.menuapp.ui.main.adapter.ViewPagerAdapter
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodViewodel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_choose_menu.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ChooseMenuActivity : AppCompatActivity() {
    private lateinit var orderViewModel: OrderViewModel
    private val foodViewodel: FoodViewodel by viewModels()
    private val drinkViewodel: DrinkViewModel by viewModels()
    private var foodName = ""
    private var drinkName = ""
    private var foodPrice = 0
    private var drinkPrice = 0
    private var totalprice = 0

    companion object {
        private const val NOTIFICATION_ID = 2
        private const val CHANNEL_ID = "channel_02"
        private const val CHANNEL_NAME = "order channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)

        backButtonImageView.setOnClickListener {
            onBackPressed()
        }

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.currentItem = intent.getIntExtra("POSITION", 0)
        tabs.setupWithViewPager(viewPager)

        foodViewodel.selectedFood.observe(this, Observer { item ->
            foodName = item.nama!!
            foodPrice = item.harga!!
            totalprice = foodPrice + drinkPrice
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val price = formatRupiah.format(totalprice).replace("Rp", "Rp ")
            totalPriceTextView.text = price
        })

        drinkViewodel.selectedDrink.observe(this, Observer { item ->
            drinkName = item.nama!!
            drinkPrice = item.harga!!
            totalprice = foodPrice + drinkPrice
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val price = formatRupiah.format(totalprice).replace("Rp", "Rp ")
            totalPriceTextView.text = price
        })

        btnCancel.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }


        btnOrder.setOnClickListener {
            val timestamp = System.currentTimeMillis()
            val orderEntity = OrderEntity(0, timestamp, foodName, foodPrice, drinkName, drinkPrice, totalprice)
            orderViewModel.addOrder(orderEntity)
            sendNotification(foodName, drinkName)
            finish()
        }
    }

    private fun sendNotification(foodName: String, drinkName: String) {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.burger)
                .setContentTitle("Pesanan Baru")
                .setContentText("$foodName dan $drinkName berhasi dipesan")
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_NAME
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "Pilih menu kesukaanmu", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Order diproses", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "Sistem berhenti sementara", Toast.LENGTH_SHORT).show()
    }
}