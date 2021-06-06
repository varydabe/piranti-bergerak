package com.lebahakatsuki.menuapp.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.Menu
import com.lebahakatsuki.menuapp.ui.main.adapter.ListFoodDetailAdapter
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodViewModel

//Food Fragment
class FoodFragment : Fragment() {
    lateinit var recyclerviewFoodDetail: RecyclerView
    lateinit var foodFragmentViewModel: FoodFragmentViewModel
    lateinit var adapter: ListFoodDetailAdapter
    private val foodViewModel: FoodViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //set viewmodel dan recyclerview serta food data.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_food, container, false)

        adapter = ListFoodDetailAdapter()
        recyclerviewFoodDetail = v.findViewById(R.id.recyclerviewFoodDetail)
        recyclerviewFoodDetail.adapter = adapter
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        foodFragmentViewModel = ViewModelProviders.of(this).get(FoodFragmentViewModel::class.java)

        foodFragmentViewModel.getFood().observe(viewLifecycleOwner, Observer {
            val listFood: List<Menu>? = it.values
            adapter.setData(listFood!!)
        })

        adapter.onItemClick = { menu, position ->
            onRecyclerViewClick(menu, position)
            if (menu != null) {
                onItemClick(menu)
            }
        }
        return v
    }

    //set element onclik
    private fun onRecyclerViewClick(menu: Menu?, position: Int) {
        var data = menu
        data?.flagSelected = 1
        adapter.setSelectedPos(position)
    }

    fun onItemClick(menu: Menu) {
        foodViewModel.selectFood(menu)
    }
}