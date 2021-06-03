package com.lebahakatsuki.menuapp.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkDetailAdapter
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.data.model.Menu
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkViewModel


class DrinkFragment : Fragment() {
    lateinit var recyclerviewDrinkDetail: RecyclerView
    lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    lateinit var adapter: ListDrinkDetailAdapter
    private val drinkViewodel: DrinkViewModel by activityViewModels()
    //private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_drink, container, false)

        recyclerviewDrinkDetail = v.findViewById(R.id.recyclerviewDrinkDetail) as RecyclerView
        adapter = ListDrinkDetailAdapter()
        recyclerviewDrinkDetail.adapter = adapter
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)
        /*drinkFragmentViewModel.getAllDrink.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })*/
        drinkFragmentViewModel.getDrink().observe(viewLifecycleOwner, Observer {
            val listDrink: List<Menu>? = it.values
            adapter.setData(listDrink!!)
        })

        adapter.onItemClick = { menu, position ->
            onRecyclerViewClick(menu, position)
            if (menu != null) {
                onItemClick(menu)
            }
        }

        /*
        recyclerviewDrinkDetail.setHasFixedSize(true)

        listDrink.addAll(DrinksData.listData)
        showRecyclerDrink()*/

        return v
    }

    private fun onRecyclerViewClick(menu: Menu?, position: Int) {
        var data = menu
        data?.flagSelected = 1
        adapter.setSelectedPos(position)
    }

    fun onItemClick(menu: Menu) {
        drinkViewodel.selectDrink(menu)
    }

    /*private fun showRecyclerDrink(){
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listDrinkDetailAdapter = ListDrinkDetailAdapter(listDrink)
        recyclerviewDrinkDetail.adapter = listDrinkDetailAdapter
    }*/
}