package com.example.coroutines

import android.app.Application
import com.example.coroutines.dagger.AppComponent
import com.example.coroutines.dagger.DaggerAppComponent

open class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}