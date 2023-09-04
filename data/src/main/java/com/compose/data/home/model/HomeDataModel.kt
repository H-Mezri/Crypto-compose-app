package com.compose.data.home.model

import com.google.gson.annotations.SerializedName

data class CryptoDataModel(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("market_cap") val marketCap: Double,
    @SerializedName("market_cap_rank") val marketCapRank: Int,
    @SerializedName("total_volume") val totalVolume: Double,
    @SerializedName("high_24h") val highPrice24: Double,
    @SerializedName("low_24h") val lowPrice24: Double,
    @SerializedName("price_change_24h") val priceChange24: Double,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24: Double,
    @SerializedName("total_supply") val totalSupply: Double,
    @SerializedName("max_supply") val maxSupply: Double,
    val ath: Double,
    @SerializedName("ath_change_percentage") val athChangePercentage: Double,
    @SerializedName("ath_date") val athDate: String
)