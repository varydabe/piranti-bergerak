package com.lebahakatsuki.menuapp.ui.main.view.fragment

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
import com.lebahakatsuki.menuapp.data.model.Menu
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkViewModel

//Drink Fragment
class DrinkFragment : Fragment() {
    lateinit var recyclerviewDrinkDetail: RecyclerView
    lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    lateinit var adapter: ListDrinkDetailAdapter
    private val drinkViewModel: DrinkViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_drink, container, false)

        //setting recyclerview and get data of drink
        recyclerviewDrinkDetail = v.findViewById(R.id.recyclerviewDrinkDetail) as RecyclerView
        adapter = ListDrinkDetailAdapter()
        recyclerviewDrinkDetail.adapter = adapter
        recyclerviewDrinkDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)

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
        return v
    }

    //set data when element get click
    private fun onRecyclerViewClick(menu: Menu?, position: Int) {
        var data = menu
        data?.flagSelected = 1
        adapter.setSelectedPos(position)
    }

    //select menu on click.
    fun onItemClick(menu: Menu) {
        drinkViewModel.selectDrink(menu)
    }
}