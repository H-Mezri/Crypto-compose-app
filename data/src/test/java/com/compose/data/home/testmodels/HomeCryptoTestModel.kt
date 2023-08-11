package com.compose.data.home.testmodels

import com.compose.business.home.repository.HomeCryptoModelInterface

data class HomeCryptoTestModel(
    override val id: String,
    override val symbol: String,
    override val name: String,
    override val image: String,
    override val currentPrice: Double,
    override val marketCap: Double,
    override val marketCapRank: Int,
    override val totalVolume: Double,
    override val highPrice24: Double,
    override val lowPrice24: Double,
    override val priceChange24: Double,
    override val priceChangePercentage24: Double,
    override val totalSupply: Double,
    override val maxSupply: Double,
    override val ath: Double,
    override val athChangePercentage: Double,
    override val athDate: String
) : HomeCryptoModelInterface