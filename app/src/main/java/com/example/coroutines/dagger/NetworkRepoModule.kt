package com.example.coroutines.dagger

import com.example.coroutines.data.TickerNetworkRepositoryImpl
import com.example.coroutines.domain.TickerNetworkRepository
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkRepoModule {
    @Binds
    abstract fun provideNetworkRepository(tickerNetworkRepository: TickerNetworkRepositoryImpl): TickerNetworkRepository
}