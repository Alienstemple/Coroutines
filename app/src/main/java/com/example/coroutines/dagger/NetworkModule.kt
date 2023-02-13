package com.example.coroutines.dagger

import com.example.coroutines.data.TickerNetworkRepositoryImpl
import com.example.coroutines.domain.TickerNetworkRepository
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {
    @Binds
    abstract fun provideStorage(tickerNetworkRepository: TickerNetworkRepositoryImpl): TickerNetworkRepository
}