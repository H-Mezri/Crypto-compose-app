package com.compose.data.home.mapper

import com.compose.data.home.testmodels.HomeCryptoModelTestProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test

class HomeCryptoBusinessMapper {

    @Test
    fun GIVEN_CryptoDataModel_WHEN_toBusinessModel_THEN_return_correct_model() {
        val inputCryptoDataModel =
            HomeCryptoModelTestProvider().getHomeCryptoTestModels(1, false).first()

        val resultCryptoBusinessModel = inputCryptoDataModel.toBusinessModel()

        assertEquals(inputCryptoDataModel.id, resultCryptoBusinessModel.id)
        assertEquals(inputCryptoDataModel.symbol, resultCryptoBusinessModel.symbol)
        assertEquals(inputCryptoDataModel.name, resultCryptoBusinessModel.title)
        assertEquals(inputCryptoDataModel.image, resultCryptoBusinessModel.image)
        assertEquals(inputCryptoDataModel.currentPrice, resultCryptoBusinessModel.currentPrice)
        assertEquals(inputCryptoDataModel.marketCap, resultCryptoBusinessModel.marketCap)
        assertEquals(inputCryptoDataModel.marketCapRank, resultCryptoBusinessModel.marketCapRank)
        assertEquals(inputCryptoDataModel.totalVolume, resultCryptoBusinessModel.totalVolume)
        assertEquals(inputCryptoDataModel.highPrice24, resultCryptoBusinessModel.highPrice24)
        assertEquals(inputCryptoDataModel.lowPrice24, resultCryptoBusinessModel.lowPrice24)
        assertEquals(inputCryptoDataModel.priceChange24, resultCryptoBusinessModel.priceChange24)
        assertEquals(
            inputCryptoDataModel.priceChangePercentage24,
            resultCryptoBusinessModel.priceChangePercentage24
        )
        assertEquals(inputCryptoDataModel.totalSupply, resultCryptoBusinessModel.totalSupply)
        assertEquals(inputCryptoDataModel.maxSupply, resultCryptoBusinessModel.maxSupply)
        assertEquals(inputCryptoDataModel.ath, resultCryptoBusinessModel.ath)
        assertEquals(
            inputCryptoDataModel.athChangePercentage,
            resultCryptoBusinessModel.athChangePercentage
        )
        assertEquals(inputCryptoDataModel.athDate, resultCryptoBusinessModel.athDate)
    }
}