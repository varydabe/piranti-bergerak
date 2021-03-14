package com.lebahakatsuki.menuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.adapter.ListFoodAdapter
import com.lebahakatsuki.menuapp.adapter.ListFoodDetailAdapter
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
    }

    private fun showRecyclerFood(){
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listFoodDetailAdapter = ListFoodDetailAdapter(listFood)
        recyclerviewFoodDetail.adapter = listFoodDetailAdapter
    }
}