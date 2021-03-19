package com.lebahakatsuki.menuapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.adapter.ListFoodDetailAdapter
import com.lebahakatsuki.menuapp.data.FoodsData
import com.lebahakatsuki.menuapp.model.FoodDrink

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : Fragment() {
    lateinit var recyclerviewFoodDetail: RecyclerView
    private var listFood: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_food, container, false)

        recyclerviewFoodDetail = v.findViewById(R.id.recyclerviewFoodDetail)
        recyclerviewFoodDetail.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()

        return v
    }

    private fun showRecyclerFood(){
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val listFoodDetailAdapter = ListFoodDetailAdapter(listFood)
        recyclerviewFoodDetail.adapter = listFoodDetailAdapter
    }
}