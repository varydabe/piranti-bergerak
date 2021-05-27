package com.lebahakatsuki.menuapp

import android.app.Application
import com.lebahakatsuki.menuapp.data.network.RetrofitApi
import com.lebahakatsuki.menuapp.data.network.RetrofitClient
import com.lebahakatsuki.menuapp.ui.main.viewmodel.AddMenuViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
    }
}