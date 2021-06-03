package com.lebahakatsuki.menuapp.ui.main.view

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonObject
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.ui.main.viewmodel.AddMenuViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel
import kotlinx.android.synthetic.main.activity_add_menu.*

class AddMenuActivity : AppCompatActivity() {
    private lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private lateinit var addMenuViewModel: AddMenuViewModel
    private var category: String = ""

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "menu channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        foodFragmentViewModel = ViewModelProvider(this).get(FoodFragmentViewModel::class.java)
        drinkFragmentViewModel = ViewModelProvider(this).get(DrinkFragmentViewModel::class.java)
        addMenuViewModel = ViewModelProvider(this).get(AddMenuViewModel::class.java)

        backButtonImageView.setOnClickListener {
            onBackPressed()
        }

        addButton.setOnClickListener {
            val checkedId = categoryRadioGroup.checkedRadioButtonId
            if (checkedId == -1){
                Toast.makeText(it.context, "Harus memilih salah satu", Toast.LENGTH_SHORT).show()
            } else {
                findRadioButton(checkedId)
                val nama = menuEditText.text.toString()
                val harga = priceEditText.text.toString().trim().toInt()
                if (nama.isNullOrEmpty()) {
                    menuEditText.error = "Nama menu harus diisi"
                } else if (harga.toString().isNullOrEmpty()) {
                    priceEditText.error = "Harga harus diisi"
                } else {
                    val addMenuRequestModel = AddMenuRequestModel(nama, harga, category)
                    addMenuViewModel.addMenu(addMenuRequestModel)
                    sendNotification(nama)
                    finish()
                    //insertDataToDatabase(menuEditText.text.toString(), category,priceEditText.text.toString())
                }
            }
        }
    }

    private fun findRadioButton(checkedId: Int) {
        when(checkedId){
            R.id.foodRadioButton -> category = "food"
            R.id.drinkRadioButton -> category = "drink"
            else -> Toast.makeText(applicationContext, "Harus memilih salah satu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertDataToDatabase(menuName: String, category: String, price: String) {
        if (inputCheck(menuName, category, price)){
            if (category.equals("Food")){
                val foodEntity = FoodEntity(0, menuName, price)
                foodFragmentViewModel.addFood(foodEntity)
                Toast.makeText(applicationContext, "Menu berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else if (category.equals("Drink")){
                val drinkEntity = DrinkEntity(0, menuName, price)
                drinkFragmentViewModel.addDrink(drinkEntity)
                Toast.makeText(applicationContext, "Menu berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
    }

    private fun inputCheck(menuName: String, category: String, price: String): Boolean{
        return !(TextUtils.isEmpty(menuName) && TextUtils.isEmpty(category) && TextUtils.isEmpty(price))
    }

    private fun sendNotification(name: String) {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.burger)
            .setContentTitle("Menu Baru")
            .setContentText("$name berhasi ditambahkan")
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
}