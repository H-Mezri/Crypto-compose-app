package com.compose.data.home.model

import com.compose.business.home.repository.HomeCryptoModelInterface
import com.google.gson.annotations.SerializedName

data class HomeCryptoModel(
    override val id: String,
    override val symbol: String,
    override val name: String,
    override val image: String,
    @SerializedName("current_price") override val currentPrice: Double,
    @SerializedName("market_cap") override val marketCap: Double,
    @SerializedName("market_cap_rank") override val marketCapRank: Int,
    @SerializedName("total_volume") override val totalVolume: Double,
    @SerializedName("high_24h") override val highPrice24: Double,
    @SerializedName("low_24h") override val lowPrice24: Double,
    @SerializedName("price_change_24h") override val priceChange24: Double,
    @SerializedName("price_change_percentage_24h") override val priceChangePercentage24: Double,
    @SerializedName("total_supply") override val totalSupply: Double,
    @SerializedName("max_supply") override val maxSupply: Double,
    override val ath: Double,
    @SerializedName("ath_change_percentage") override val athChangePercentage: Double,
    @SerializedName("ath_date") override val athDate: String
) : HomeCryptoModelInterface