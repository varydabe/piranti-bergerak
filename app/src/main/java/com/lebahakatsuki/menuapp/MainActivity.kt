package com.lebahakatsuki.menuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.adapter.ListDrinkAdapter
import com.lebahakatsuki.menuapp.adapter.ListFoodAdapter
import com.lebahakatsuki.menuapp.data.DrinksData
import com.lebahakatsuki.menuapp.data.FoodsData
import com.lebahakatsuki.menuapp.model.FoodDrink

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerviewFood: RecyclerView
    private lateinit var recyclerviewDrink: RecyclerView
    private var listFood: ArrayList<FoodDrink> = arrayListOf()
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerviewFood = findViewById(R.id.recyclerviewFood)
        recyclerviewFood.setHasFixedSize(true)
        recyclerviewDrink = findViewById(R.id.recyclerviewDrink)
        recyclerviewDrink.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()
        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()
    }

    private fun showRecyclerFood(){
        recyclerviewFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(listFood)
        recyclerviewFood.adapter = listFoodAdapter
    }

    private fun showRecyclerDrink(){
        recyclerviewDrink.layoutManager = LinearLayoutManager(this)
        val listDrinkAdapter = ListDrinkAdapter(listDrink)
        recyclerviewDrink.adapter = listDrinkAdapter
    }
}