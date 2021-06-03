package com.lebahakatsuki.menuapp.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ViewPagerAdapter
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodViewodel
import kotlinx.android.synthetic.main.activity_choose_menu.*
import java.text.NumberFormat
import java.util.*

class ChooseMenuActivity : AppCompatActivity() {
    private val foodViewodel: FoodViewodel by viewModels()
    private val drinkViewodel: DrinkViewModel by viewModels()
    private var foodPrice = 0
    private var drinkPrice = 0
    private var totalprice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.currentItem = intent.getIntExtra("POSITION", 0)
        tabs.setupWithViewPager(viewPager)

        foodViewodel.selectedFood.observe(this, Observer { item ->
            foodPrice = item.harga!!
            totalprice = foodPrice + drinkPrice
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val price = formatRupiah.format(totalprice).replace("Rp", "Rp ")
            totalPriceTextView.text = price
        })

        drinkViewodel.selectedDrink.observe(this, Observer { item ->
            drinkPrice = item.harga!!
            totalprice = foodPrice + drinkPrice
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val price = formatRupiah.format(totalprice).replace("Rp", "Rp ")
            totalPriceTextView.text = price
        })

        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnCancel.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }


        btnOrder.setOnClickListener {
            finish()
        }
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