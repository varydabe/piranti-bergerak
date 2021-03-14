package com.lebahakatsuki.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.adapter.ListDrinkAdapter
import com.lebahakatsuki.menuapp.adapter.ListDrinkDetailAdapter
import com.lebahakatsuki.menuapp.data.DrinksData
import com.lebahakatsuki.menuapp.data.FoodsData
import com.lebahakatsuki.menuapp.model.FoodDrink

class BeverageActivity : AppCompatActivity() {
    private lateinit var recyclerviewDrinkDetail: RecyclerView
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)


        recyclerviewDrinkDetail = findViewById(R.id.recyclerviewDrinkDetail)
        recyclerviewDrinkDetail.setHasFixedSize(true)

        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()

        recyclerviewDrinkDetail.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showRecyclerDrink(){
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listDrinkAdapter = ListDrinkDetailAdapter(listDrink)
        recyclerviewDrinkDetail.adapter = listDrinkAdapter
    }
}