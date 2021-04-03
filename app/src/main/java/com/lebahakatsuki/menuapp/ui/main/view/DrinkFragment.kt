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
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkDetailAdapter
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DrinkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrinkFragment : Fragment() {
    lateinit var recyclerviewDrinkDetail: RecyclerView
    lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()
    private var drinkAdapter: ListDrinkDetailAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_drink, container, false)

        recyclerviewDrinkDetail = v.findViewById(R.id.recyclerviewDrinkDetail) as RecyclerView

        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)
        drinkFragmentViewModel.getArrayList().observe(viewLifecycleOwner, Observer {

            drinkAdapter = ListDrinkDetailAdapter(it)
            recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerviewDrinkDetail.adapter = drinkAdapter
        })

        /*
        recyclerviewDrinkDetail.setHasFixedSize(true)

        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()*/

        return v
    }

    private fun showRecyclerDrink(){
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listDrinkDetailAdapter = ListDrinkDetailAdapter(listDrink)
        recyclerviewDrinkDetail.adapter = listDrinkDetailAdapter
    }
}