package com.compose.business.home.repository

import com.compose.business.home.model.CryptoBusinessModel

interface HomeRepositoryInterface {
    suspend fun fetchHomeData(): RepositoryResponse<List<CryptoBusinessModel>>
}