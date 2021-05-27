package com.lebahakatsuki.menuapp.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.ui.main.viewmodel.AddMenuViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.DrinkFragmentViewModel
import com.lebahakatsuki.menuapp.ui.main.viewmodel.FoodFragmentViewModel

class AddMenuActivity : AppCompatActivity() {
    private lateinit var foodFragmentViewModel: FoodFragmentViewModel
    private lateinit var drinkFragmentViewModel: DrinkFragmentViewModel
    private lateinit var addMenuViewModel: AddMenuViewModel
    private var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        foodFragmentViewModel = ViewModelProvider(this).get(FoodFragmentViewModel::class.java)
        drinkFragmentViewModel = ViewModelProvider(this).get(DrinkFragmentViewModel::class.java)
        addMenuViewModel = ViewModelProvider(this).get(AddMenuViewModel::class.java)
        val menuEditText = findViewById<EditText>(R.id.menuEditText)
        val priceEditText = findViewById<EditText>(R.id.priceEditText)
        val categoryRadioGroup = findViewById<RadioGroup>(R.id.categoryRadioGroup)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            var checkedId = categoryRadioGroup.checkedRadioButtonId
            if (checkedId == -1){
                Toast.makeText(it.context, "Harus memilih salah satu", Toast.LENGTH_SHORT).show()
            } else {
                findRadioButton(checkedId)
                val addMenuRequestModel = AddMenuRequestModel(menuEditText.text.toString(), priceEditText.text.toString().toInt(), category)
                addMenuViewModel.addMenu(addMenuRequestModel)
                insertDataToDatabase(menuEditText.text.toString(), category,priceEditText.text.toString())
            }
        }
    }

    private fun findRadioButton(checkedId: Int) {
        when(checkedId){
            R.id.foodRadioButton -> category = "Food"
            R.id.drinkRadioButton -> category = "Drink"
            else -> Toast.makeText(applicationContext, "Harus memilih salah satu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertDataToDatabase(menuName: String, category: String, price: String) {
        if (inputCheck(menuName, category, price)){
            if (category.equals("Food")){
                val foodEntity = FoodEntity(0, menuName, price)
                foodFragmentViewModel.addFood(foodEntity)
                Toast.makeText(applicationContext, "Menu berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else if (category.equals("Drink")){
                val drinkEntity = DrinkEntity(0, menuName, price)
                drinkFragmentViewModel.addDrink(drinkEntity)
                Toast.makeText(applicationContext, "Menu berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
    }

    private fun inputCheck(menuName: String, category: String, price: String): Boolean{
        return !(TextUtils.isEmpty(menuName) && TextUtils.isEmpty(category) && TextUtils.isEmpty(price))
    }
}