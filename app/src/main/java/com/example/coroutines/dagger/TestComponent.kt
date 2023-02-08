package com.example.coroutines.dagger

import com.example.coroutines.presentation.MainActivity
import dagger.Component

@Component
interface TestComponent {
    fun inject(mainActivity: MainActivity)
}