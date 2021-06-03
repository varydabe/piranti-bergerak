package com.lebahakatsuki.menuapp.ui.main.view

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
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodViewodel

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : Fragment() {
    lateinit var recyclerviewFoodDetail: RecyclerView
    lateinit var foodFragmentViewModel: FoodFragmentViewModel
    lateinit var adapter: ListFoodDetailAdapter
    private val foodViewodel: FoodViewodel by activityViewModels()
    //private var listFood: ArrayList<FoodDrink> = arrayListOf()

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
        adapter = ListFoodDetailAdapter()
        recyclerviewFoodDetail.adapter = adapter
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        foodFragmentViewModel = ViewModelProviders.of(this).get(FoodFragmentViewModel::class.java)
        /*foodFragmentViewModel.getAllFood.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })*/
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

        /*
        recyclerviewFoodDetail.setHasFixedSize(true)

        listFood.addAll(FoodsData.listData)
        showRecyclerFood()*/

        return v
    }

    private fun onRecyclerViewClick(menu: Menu?, position: Int) {
        var data = menu
        data?.flagSelected = 1
        adapter.setSelectedPos(position)
    }

    fun onItemClick(menu: Menu) {
        foodViewodel.selectFood(menu)
    }

    /*private fun showRecyclerFood(){
        recyclerviewFoodDetail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val listFoodDetailAdapter = ListFoodDetailAdapter(listFood)
        recyclerviewFoodDetail.adapter = listFoodDetailAdapter
    }*/
}