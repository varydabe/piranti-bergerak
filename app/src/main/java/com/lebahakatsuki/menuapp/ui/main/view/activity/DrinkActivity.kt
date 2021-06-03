package com.lebahakatsuki.menuapp.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkDetailAdapter
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.model.FoodDrink

class DrinkActivity : AppCompatActivity() {
    private lateinit var recyclerviewDrinkDetail: RecyclerView
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        recyclerviewDrinkDetail = findViewById(R.id.recyclerviewDrinkDetail)
        recyclerviewDrinkDetail.setHasFixedSize(true)

        listDrink.addAll(DrinksData.listData)
        //showRecyclerDrink()
    }

    /*private fun showRecyclerDrink(){
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listDrinkDetailAdapter = ListDrinkDetailAdapter(listDrink)
        recyclerviewDrinkDetail.adapter = listDrinkDetailAdapter
    }*/
}