package com.lebahakatsuki.menuapp.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lebahakatsuki.menuapp.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        backButtonImageView.setOnClickListener {
            onBackPressed()
        }
    }
}