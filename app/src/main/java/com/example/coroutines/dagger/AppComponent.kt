package com.example.coroutines.dagger

import android.content.Context
import com.example.coroutines.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}
