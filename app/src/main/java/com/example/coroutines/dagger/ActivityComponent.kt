package com.example.coroutines.dagger

import com.example.coroutines.presentation.TickerDetailsFragment
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(fragment: TickerDetailsFragment)
}