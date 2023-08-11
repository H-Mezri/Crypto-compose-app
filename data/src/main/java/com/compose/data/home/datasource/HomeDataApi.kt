package com.compose.data.home.datasource

import com.compose.data.home.model.HomeCryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeDataApi {
    @GET
    suspend fun fetch(@Url url: String): Response<List<HomeCryptoModel>>
}