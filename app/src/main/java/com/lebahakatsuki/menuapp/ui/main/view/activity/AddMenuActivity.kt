package com.lebahakatsuki.menuapp.ui.main.view.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.ui.main.viewmodel.AddMenuViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_menu.*

@AndroidEntryPoint
class AddMenuActivity : AppCompatActivity() {
    private lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private lateinit var addMenuViewModel: AddMenuViewModel
    private var category: String = ""

    // Notification setting
    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "menu channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        //get viewmodel
        foodFragmentViewModel = ViewModelProvider(this).get(FoodFragmentViewModel::class.java)
        drinkFragmentViewModel = ViewModelProvider(this).get(DrinkFragmentViewModel::class.java)
        addMenuViewModel = ViewModelProvider(this).get(AddMenuViewModel::class.java)

        backButtonImageView.setOnClickListener {
            onBackPressed()
        }

        //setting onclicklistener and then send notification
        addButton.setOnClickListener {
            val checkedId = categoryRadioGroup.checkedRadioButtonId

            //harus memilih menu -> click order
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
                    //create addmenurequestmodel
                    val addMenuRequestModel = AddMenuRequestModel(nama, harga, category)
                    addMenuViewModel.addMenu(addMenuRequestModel)
                    sendNotification(nama)
                    finish()
                }
            }
        }
    }

    //setting up radio button for food and drink
    private fun findRadioButton(checkedId: Int) {
        when(checkedId){
            R.id.foodRadioButton -> category = "food"
            R.id.drinkRadioButton -> category = "drink"
            else -> Toast.makeText(applicationContext, "Harus memilih salah satu", Toast.LENGTH_SHORT).show()
        }
    }

    //Add menu to database (not used right now because add menu to API using POST)
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

    //Memastikan kalau input tidak kosong.
    private fun inputCheck(menuName: String, category: String, price: String): Boolean{
        return !(TextUtils.isEmpty(menuName) && TextUtils.isEmpty(category) && TextUtils.isEmpty(price))
    }

    //Mengirimkan notifikasi ketika add menu.
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