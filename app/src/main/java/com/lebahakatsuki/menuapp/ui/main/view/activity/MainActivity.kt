package com.lebahakatsuki.menuapp.ui.main.view.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.ui.main.adapter.ListDrinkAdapter
import com.lebahakatsuki.menuapp.ui.main.adapter.ListFoodAdapter
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.MenuActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var menuActivityViewModel: MenuActivityViewModel
    private lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private var listFood: ArrayList<FoodDrink> = arrayListOf()
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivHistory.setOnClickListener {
            it.context.startActivity(Intent(it.context, HistoryActivity::class.java))
        }

        ivFoodIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 0)
            it.context.startActivity(intent)
        }
        ivDrinkIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 1)
            it.context.startActivity(intent)
        }
        tvFoodMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 0)
            it.context.startActivity(intent)
        }
        tvDrinkMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 1)
            it.context.startActivity(intent)
        }

        val myFabSrc = resources.getDrawable(R.drawable.ic_add)
        val willBeWhite = myFabSrc.constantState!!.newDrawable()
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        floatingActionBtn.setImageDrawable(willBeWhite)

        menuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel::class.java)
        foodFragmentViewModel = ViewModelProviders.of(this).get(FoodFragmentViewModel::class.java)
        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)

        recyclerviewFood.setHasFixedSize(true)

        /*menuActivityViewModel.getFoodArrayList().observe(this, Observer {

            val listFoodAdapter = ListFoodAdapter(it)
            recyclerviewFood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewFood.adapter = listFoodAdapter
        })*/
        foodFragmentViewModel.getFood().observe(this, Observer {
            val listFood = it.values
            val listFoodAdapter = ListFoodAdapter()
            listFoodAdapter.setData(listFood!!)
            recyclerviewFood.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerviewFood.adapter = listFoodAdapter
        })

        recyclerviewDrink.setHasFixedSize(true)

        /*menuActivityViewModel.getDrinkArrayList().observe(this, Observer {

            val listDrinkAdapter = ListDrinkAdapter(it)
            recyclerviewDrink.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewDrink.adapter = listDrinkAdapter
        })*/
        drinkFragmentViewModel.getDrink().observe(this, Observer {
            val listDrink = it.values
            val listDrinkAdapter = ListDrinkAdapter()
            listDrinkAdapter.setData(listDrink!!)
            recyclerviewDrink.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerviewDrink.adapter = listDrinkAdapter
        })

        floatingActionBtn.setOnClickListener {
            it.context.startActivity(Intent(it.context, AddMenuActivity::class.java))
        }

//        listFood.addAll(FoodsData.listData)
//        showRecyclerFood()
//        listDrink.addAll(DrinksData.listData)
//        showRecyclerDrink()
    }

    /*private fun showRecyclerFood(){
        recyclerviewFood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listFoodAdapter = ListFoodAdapter(listFood)
        recyclerviewFood.adapter = listFoodAdapter
    }

    private fun showRecyclerDrink(){
        recyclerviewDrink.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listDrinkAdapter = ListDrinkAdapter(listDrink)
        recyclerviewDrink.adapter = listDrinkAdapter
    }*/

    private fun navigateToFood() {
        val intent = Intent(this, FoodActivity::class.java)

        startActivity(intent)
    }

    private fun navigateToDrink() {
        val intent = Intent(this, DrinkActivity::class.java)

        startActivity(intent)
    }
}