package com.lebahakatsuki.menuapp.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.ui.main.adapter.OrderAdapter
import com.lebahakatsuki.menuapp.ui.main.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_history.*

//History Order Activity
class HistoryActivity : AppCompatActivity() {
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        backButtonImageView.setOnClickListener {
            onBackPressed()
        }

        //set orderviewmodel -> get all order lalu tampilkan menggunakna recyclerview.
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        orderViewModel.getAllOrder.observe(this, Observer {
            val orderAdapter = OrderAdapter()
            orderAdapter.setData(it)
            recyclerviewOrder.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerviewOrder.adapter = orderAdapter
            Log.d("DATA", it.toString())
        })
    }
}