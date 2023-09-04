package com.compose.business.home.mapper

import com.compose.business.home.model.CryptoBusinessModel
import com.compose.business.home.model.CryptoViewModel

class CryptoViewModelMapper {

    fun toViewModel(
        cryptoDataModel: CryptoBusinessModel,
        cryptoLogoUrl: String
    ): CryptoViewModel {
        return CryptoViewModel(
            id = cryptoDataModel.id.hashCode(),
            symbol = cryptoDataModel.symbol,
            logo = getCryptoLogoUrl(cryptoLogoUrl, cryptoDataModel.symbol),
            title = cryptoDataModel.title,
            image = cryptoDataModel.image,
            currentPrice = cryptoDataModel.currentPrice,
            marketCap = cryptoDataModel.marketCap,
            marketCapRank = cryptoDataModel.marketCapRank,
            totalVolume = cryptoDataModel.totalVolume,
            highPrice24 = cryptoDataModel.highPrice24,
            lowPrice24 = cryptoDataModel.lowPrice24,
            priceChange24 = cryptoDataModel.priceChange24,
            priceChangePercentage24 = cryptoDataModel.priceChangePercentage24,
            totalSupply = cryptoDataModel.totalSupply,
            maxSupply = cryptoDataModel.maxSupply,
            ath = cryptoDataModel.ath,
            athChangePercentage = cryptoDataModel.athChangePercentage,
            athDate = cryptoDataModel.athDate
        )
    }

    private fun getCryptoLogoUrl(cryptoLogoUrl: String, symbol: String): String {
        return cryptoLogoUrl.replace(CRYPTO_URL_ID_KEY, symbol.lowercase())
    }

    companion object {
        const val CRYPTO_URL_ID_KEY = "[cryptoID]"
    }
}