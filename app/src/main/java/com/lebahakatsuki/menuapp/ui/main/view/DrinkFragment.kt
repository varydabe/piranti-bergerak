package com.lebahakatsuki.menuapp.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkDetailAdapter
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.model.FoodDrink

class DrinkFragment : Fragment() {
    lateinit var recyclerviewDrinkDetail: RecyclerView
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_drink, container, false)

        recyclerviewDrinkDetail = v.findViewById(R.id.recyclerviewDrinkDetail)
        recyclerviewDrinkDetail.setHasFixedSize(true)

        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()

        return v
    }

    private fun showRecyclerDrink(){
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listDrinkDetailAdapter = ListDrinkDetailAdapter(listDrink)
        recyclerviewDrinkDetail.adapter = listDrinkDetailAdapter
    }
}