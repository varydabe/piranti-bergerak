package com.lebahakatsuki.menuapp.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ListFoodDetailAdapter
import com.lebahakatsuki.menuapp.data.resource.FoodsData
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : Fragment() {
    lateinit var recyclerviewFoodDetail: RecyclerView
    lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private var listFood: ArrayList<FoodDrink> = arrayListOf()
    private var foodAdapter: ListFoodDetailAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_food, container, false)

        recyclerviewFoodDetail = v.findViewById(R.id.recyclerviewFoodDetail) as RecyclerView

        foodFragmentViewModel = ViewModelProviders.of(this).get(FoodFragmentViewModel::class.java)
        foodFragmentViewModel.getArrayList().observe(viewLifecycleOwner, Observer {

            foodAdapter = ListFoodDetailAdapter(it)
            recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerviewFoodDetail.adapter = foodAdapter
        })

        /*
        recyclerviewFoodDetail.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()*/

        return v
    }

    private fun showRecyclerFood(){
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val listFoodDetailAdapter = ListFoodDetailAdapter(listFood)
        recyclerviewFoodDetail.adapter = listFoodDetailAdapter
    }
}