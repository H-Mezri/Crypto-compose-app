package com.compose.platform.home.model

data class CryptoHomeViewElement(
    val id: Int,
    val symbol: String,
    val title: String,
    val logoUrl: String,
    val image: String,
    val currentPrice: Double,
    val marketCap: Double,
    val marketCapRank: Int,
    val totalVolume: Double,
    val highPrice24: Double,
    val lowPrice24: Double,
    val priceChange24: Double,
    val priceChangePercentage24: Double,
    val totalSupply: Double,
    val maxSupply: Double,
    val ath: Double,
    val athChangePercentage: Double,
    val athDate: String
)