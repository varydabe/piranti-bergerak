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
import com.lebahakatsuki.menuapp.adapter.ListFoodAdapter
import com.lebahakatsuki.menuapp.data.DrinksData
import com.lebahakatsuki.menuapp.data.FoodsData
import com.lebahakatsuki.menuapp.model.FoodDrink

class FoodActivity : AppCompatActivity() {
    private lateinit var recyclerviewFoodDetail: RecyclerView
    private var listFood: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        recyclerviewFoodDetail = findViewById(R.id.recyclerviewFoodDetail)
        recyclerviewFoodDetail.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()

        recyclerviewFoodDetail.setOnClickListener {
            val intent = Intent(applicationContext, BeverageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showRecyclerFood(){
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listFoodAdapter = ListFoodAdapter(listFood)
        recyclerviewFoodDetail.adapter = listFoodAdapter
    }
}