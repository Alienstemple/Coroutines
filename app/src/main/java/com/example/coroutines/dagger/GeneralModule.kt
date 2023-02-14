package com.example.coroutines.dagger

import com.example.coroutines.data.*
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.domain.TickerInteractorImpl
import com.example.coroutines.domain.TickerNetworkRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralModule {

    val mapper: ObjectMapper = jacksonObjectMapper()
    val apiClient = RetrofitService.getInstance()

    @Provides
    @Singleton
    fun providesTickerNetworkService(): TickerNetworkService {
        return TickerNetworkService(apiClient)
    }

    @Provides
    @Singleton
    fun providesTickerFileService(): TickerFileService {
        return TickerFileService(mapper)
    }

    @Provides
    @Singleton
    fun providesTickerNetworkRepository(tickerNetworkService: TickerNetworkService): TickerNetworkRepository {
        return TickerNetworkRepositoryImpl(tickerNetworkService)
    }

    @Provides
    @Singleton
    fun providesTickerFileRepository(tickerFileService: TickerFileService): TickerFileRepository {
        return TickerFileRepositoryImpl(tickerFileService)
    }

    @Provides
    @Singleton
    fun providesInteractor(
        tickerFileRepository: TickerFileRepository,
        tickerNetworkRepository: TickerNetworkRepository,
    ): TickerInteractor {
        return TickerInteractorImpl(tickerNetworkRepository, tickerFileRepository)
    }
}