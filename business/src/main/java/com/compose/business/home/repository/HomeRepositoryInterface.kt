package com.compose.business.home.repository

interface HomeRepositoryInterface {
    suspend fun fetchHomeData(): RepositoryResponseInterface<List<HomeCryptoModelInterface>>
}