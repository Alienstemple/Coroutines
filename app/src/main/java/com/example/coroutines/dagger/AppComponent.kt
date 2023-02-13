package com.example.coroutines.dagger

import android.content.Context
import com.example.coroutines.data.RetrofitService
import com.example.coroutines.data.TickerNetworkService
import com.example.coroutines.presentation.MainActivity
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, InteractorModule::class, FileRepoModule::class, NetworkRepoModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance  mapper: ObjectMapper,
                   @BindsInstance  tickerApi: RetrofitService): AppComponent
    }

    fun activityComponent(): ActivityComponent.Factory

    fun inject(activity: MainActivity)
}
