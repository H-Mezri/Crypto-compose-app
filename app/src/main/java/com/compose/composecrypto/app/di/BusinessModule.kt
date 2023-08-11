package com.compose.composecrypto.app.di

import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.HomeUsesCase
import com.compose.business.home.HomeUsesCaseInterface
import com.compose.business.home.mapper.CryptoBusinessModelMapper
import com.compose.data.remoteconfig.RemoteConfigRepository
import org.koin.dsl.module

val businessModule = module {
    factory { CryptoBusinessModelMapper() }
    single<RemoteConfigGatewayInterface> { RemoteConfigRepository() }
    single<HomeUsesCaseInterface> {
        HomeUsesCase(
            homeRepository = get(),
            remoteConfigGateway = get(),
            cryptoBusinessModelMapper = get()
        )
    }
}