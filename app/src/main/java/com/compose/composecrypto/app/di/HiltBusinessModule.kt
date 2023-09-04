package com.compose.composecrypto.app.di

import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.HomeUsesCase
import com.compose.business.home.HomeUsesCaseInterface
import com.compose.business.home.mapper.CryptoViewModelMapper
import com.compose.business.home.repository.HomeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltBusinessModule {

    @Provides
    fun provideCryptoBusinessModelMapper() = CryptoViewModelMapper()

    @Provides
    fun provideHomeUsesCase(
        homeRepository: HomeRepositoryInterface,
        remoteConfigGateway: RemoteConfigGatewayInterface,
        cryptoViewModelMapper: CryptoViewModelMapper
    ): HomeUsesCaseInterface =
        HomeUsesCase(homeRepository, remoteConfigGateway, cryptoViewModelMapper)
}