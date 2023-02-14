package com.example.coroutines

import android.app.Application
import android.util.Log
import com.example.coroutines.dagger.AppComponent
import com.example.coroutines.dagger.DaggerAppComponent
import com.example.coroutines.data.RetrofitService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

open class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
       DaggerAppComponent.factory().create(applicationContext)
    }
}