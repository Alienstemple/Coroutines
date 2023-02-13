package com.example.coroutines.dagger

import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.domain.TickerInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class InteractorModule {
    @Binds
    @Singleton
    abstract fun provideTickerInteractor(tickerInteractorImpl: TickerInteractorImpl): TickerInteractor
}