package com.compose.composecrypto.app.di

import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.data.home.datasource.HomeDataApi
import com.compose.data.home.datasource.HomeLocalDataSource
import com.compose.data.home.datasource.HomeRemoteDataSource
import com.compose.data.home.repositroy.HomeRepository
import com.compose.data.remoteconfig.RemoteConfigRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.coincap.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<HomeDataApi> { (get() as Retrofit).create(HomeDataApi::class.java) }

    single { OkHttpClient() }

    single { RemoteConfigRepository() }
    single<HomeRepositoryInterface> { HomeRepository(
        homeRemoteDataSource = get(),
        homeLocalDataSource = get(),
        remoteConfigRepository = get()
    ) }

    single { HomeLocalDataSource() }

    single { HomeRemoteDataSource(get()) }
}