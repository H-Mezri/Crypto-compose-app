package com.compose.data.home.mapper

import com.compose.business.home.model.CryptoBusinessModel
import com.compose.data.home.model.CryptoDataModel

fun CryptoDataModel.toBusinessModel() = CryptoBusinessModel(
    id = id,
    symbol = symbol,
    title = name,
    image = image,
    currentPrice = currentPrice,
    marketCap = marketCap,
    marketCapRank = marketCapRank,
    totalVolume = totalVolume,
    highPrice24 = highPrice24,
    lowPrice24 = lowPrice24,
    priceChange24 = priceChange24,
    priceChangePercentage24 = priceChangePercentage24,
    totalSupply = totalSupply,
    maxSupply = maxSupply,
    ath = ath,
    athChangePercentage = athChangePercentage,
    athDate = athDate
)