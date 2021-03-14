package com.lebahakatsuki.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
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
    private lateinit var foodImage: ImageView
    private lateinit var drinkImage: ImageView

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
        recyclerviewFood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listFoodAdapter = ListFoodAdapter(listFood)
        recyclerviewFood.adapter = listFoodAdapter
    }

    private fun showRecyclerDrink(){
        recyclerviewDrink.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listDrinkAdapter = ListDrinkAdapter(listDrink)
        recyclerviewDrink.adapter = listDrinkAdapter
    }

    override fun onStart() {
        super.onStart()

        findViewById<ImageView>(R.id.ivFoodIcon).setOnClickListener {
            navigateToFood()
        }

        findViewById<TextView>(R.id.tvFoodMore).setOnClickListener {
            navigateToFood()
        }

        findViewById<ImageView>(R.id.ivDrinkIcon).setOnClickListener {
            navigateToDrink()
        }

        findViewById<TextView>(R.id.tvDrinkMore).setOnClickListener {
            navigateToDrink()
        }

    }

    private fun navigateToFood() {
        val intent = Intent(this, FoodActivity::class.java)

        startActivity(intent)
    }

    private fun navigateToDrink() {
        val intent = Intent(this, DrinkActivity::class.java)

        startActivity(intent)
    }
}