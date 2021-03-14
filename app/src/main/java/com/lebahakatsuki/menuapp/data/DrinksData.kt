package com.lebahakatsuki.menuapp.data

import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.model.FoodDrink

object DrinksData {
    private val drinkNames = arrayOf("Es Teh", "Teh", "Es Jeruk", "Air Mineral", "Teh Botol")

    private val drinkPrices = arrayOf("Rp 7.000,00","Rp 7.000,00","Rp 8.000,00","Rp 5.000,00","Rp 6.000,00")

    private val drinkImages = intArrayOf(R.drawable.es_teh, R.drawable.teh, R.drawable.es_jeruk, R.drawable.air_mineral, R.drawable.teh_botol)

    val listData: ArrayList<FoodDrink>
        get(){
            val list = arrayListOf<FoodDrink>()
            for (position in drinkNames.indices){
                val foodDrink = FoodDrink()
                foodDrink.name = drinkNames[position]
                foodDrink.price = drinkPrices[position]
                foodDrink.photo = drinkImages[position]
                list.add(foodDrink)
            }
            return list
        }
}