package com.lebahakatsuki.menuapp.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkAdapter
import com.lebahakatsuki.menuapp.ui.main.adapter.ListFoodAdapter
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.resource.FoodsData
import com.lebahakatsuki.menuapp.data.model.FoodDrink

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerviewFood: RecyclerView
    private lateinit var recyclerviewDrink: RecyclerView
    private var listFood: ArrayList<FoodDrink> = arrayListOf()
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivFoodIcon = findViewById<ImageView>(R.id.ivFoodIcon)
        ivFoodIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            it.context.startActivity(intent)
        }
        val ivDrinkIcon = findViewById<ImageView>(R.id.ivDrinkIcon)
        ivDrinkIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            it.context.startActivity(intent)
        }
        val tvFoodMore = findViewById<TextView>(R.id.tvFoodMore)
        tvFoodMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            it.context.startActivity(intent)
        }
        val tvDrinkMore = findViewById<TextView>(R.id.tvDrinkMore)
        tvDrinkMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            it.context.startActivity(intent)
        }
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
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "Selamat Datang", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

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