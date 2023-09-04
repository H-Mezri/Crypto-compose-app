package com.compose.composecrypto.app.di

import com.compose.platform.home.factory.HomeFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltViewModule {

    @Provides
    fun provideHomeFactory() = HomeFactory()
}