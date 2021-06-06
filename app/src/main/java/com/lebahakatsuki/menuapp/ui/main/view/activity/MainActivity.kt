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
import kotlinx.android.synthetic.main.activity_main.*


//MAIN
class MainActivity : AppCompatActivity() {
    private lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private var listFood: ArrayList<FoodDrink> = arrayListOf()
    private var listDrink: ArrayList<FoodDrink> = arrayListOf()

    //setting setiapp button yang ada untuk berpindah ke menu detail/order history/add menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Order History
        ivHistory.setOnClickListener {
            it.context.startActivity(Intent(it.context, HistoryActivity::class.java))
        }

        //Food Detail
        ivFoodIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 0)
            it.context.startActivity(intent)
        }

        //Drink Detail
        ivDrinkIcon.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 1)
            it.context.startActivity(intent)
        }

        //Food Detail
        tvFoodMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 0)
            it.context.startActivity(intent)
        }

        //Drink Detail
        tvDrinkMore.setOnClickListener {
            val intent = Intent(it.context, ChooseMenuActivity::class.java)
            intent.putExtra("POSITION", 1)
            it.context.startActivity(intent)
        }

        //setting floating button and viewmodel.
        val myFabSrc = resources.getDrawable(R.drawable.ic_add)
        val willBeWhite = myFabSrc.constantState!!.newDrawable()
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        floatingActionBtn.setImageDrawable(willBeWhite)

        foodFragmentViewModel = ViewModelProviders.of(this).get(FoodFragmentViewModel::class.java)
        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)

        recyclerviewFood.setHasFixedSize(true)

        //Get food data from foodfragmentviewmodel
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

        //get drink data from drinkfragmentviewmodel
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

        //floating button event onclick.
        floatingActionBtn.setOnClickListener {
            it.context.startActivity(Intent(it.context, AddMenuActivity::class.java))
        }
    }
}