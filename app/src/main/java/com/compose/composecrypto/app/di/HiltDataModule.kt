package com.compose.composecrypto.app.di

import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.data.home.datasource.HomeDataApi
import com.compose.data.home.datasource.HomeLocalDataSource
import com.compose.data.home.datasource.HomeRemoteDataSource
import com.compose.data.home.repositroy.HomeRepository
import com.compose.data.remoteconfig.RemoteConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClientClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        remoteConfigRepository: RemoteConfigGatewayInterface
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(remoteConfigRepository.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHomeDataApi(retrofitClient: Retrofit): HomeDataApi {
        return retrofitClient.create(HomeDataApi::class.java)
    }

    @Provides
    fun provideHomeRemoteDataSource(homeDataApi: HomeDataApi) = HomeRemoteDataSource(homeDataApi)

    @Provides
    @Singleton
    fun provideHomeLocalDataSource() = HomeLocalDataSource()

    @Provides
    @Singleton
    fun provideRemoteConfigRepository(): RemoteConfigGatewayInterface = RemoteConfigRepository()

    @Provides
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource,
        homeLocalDataSource: HomeLocalDataSource,
        remoteConfigRepository: RemoteConfigGatewayInterface
    ): HomeRepositoryInterface =
        HomeRepository(homeRemoteDataSource, homeLocalDataSource, remoteConfigRepository)
}
