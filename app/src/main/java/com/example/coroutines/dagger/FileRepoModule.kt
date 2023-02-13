package com.example.coroutines.dagger

import com.example.coroutines.data.TickerFileRepositoryImpl
import com.example.coroutines.data.TickerNetworkRepositoryImpl
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.domain.TickerNetworkRepository
import dagger.Binds
import dagger.Module

@Module
abstract class FileRepoModule {
    @Binds
    abstract fun provideFileRepository(tickerFileRepository: TickerFileRepositoryImpl): TickerFileRepository
}