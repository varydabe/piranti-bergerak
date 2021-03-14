package com.lebahakatsuki.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation
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

        val ivFood: ImageView = findViewById(R.id.ivFood)
        val ivDrink: ImageView = findViewById(R.id.ivDrink)

        recyclerviewFood = findViewById(R.id.recyclerviewFood)
        recyclerviewFood.setHasFixedSize(true)
        recyclerviewDrink = findViewById(R.id.recyclerviewDrink)
        recyclerviewDrink.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()
        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()

        ivFood.setOnClickListener {
            val intent = Intent(applicationContext, FoodActivity::class.java)
            startActivity(intent)
        }

        ivDrink.setOnClickListener {
            val intent1 = Intent(applicationContext, BeverageActivity::class.java)
            startActivity(intent1)
        }
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
}