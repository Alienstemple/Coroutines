package com.example.coroutines.dagger

import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.domain.TickerInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {
    @Binds
    abstract fun provideTickerInteractor(tickerInteractorImpl: TickerInteractorImpl): TickerInteractor
}