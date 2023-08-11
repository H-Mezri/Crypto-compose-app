package com.compose.business.home.repository

interface HomeCryptoModelInterface {
    val id: String
    val symbol: String
    val name: String
    val image: String
    val currentPrice: Double
    val marketCap: Double
    val marketCapRank: Int
    val totalVolume: Double
    val highPrice24: Double
    val lowPrice24: Double
    val priceChange24: Double
    val priceChangePercentage24: Double
    val totalSupply: Double
    val maxSupply: Double
    val ath: Double
    val athChangePercentage: Double
    val athDate: String
}