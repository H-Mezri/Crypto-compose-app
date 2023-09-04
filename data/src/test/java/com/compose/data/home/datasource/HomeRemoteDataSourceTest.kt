package com.compose.data.home.datasource

import com.compose.business.home.repository.RepositoryFailure
import com.compose.business.home.repository.RepositorySuccess
import com.compose.data.home.model.CryptoDataModel
import com.compose.data.home.testmodels.HomeCryptoModelTestProvider
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

internal class HomeRemoteDataSourceTest {

    private val homeDataApi: HomeDataApi = mockk()

    private val homeRemoteDataSource = HomeRemoteDataSource(homeDataApi)

    private val homeCryptoModelTestProvider = HomeCryptoModelTestProvider()

    @Test
    fun GIVEN_success_data_result_WHEN_fetch_THEN_return_correct_result() = runTest {
        // given
        val url = "www.exempleurl.fr"
        val homeCryptoList =
            homeCryptoModelTestProvider.getHomeCryptoTestModels(
                5,
                false
            ) as List<CryptoDataModel>
        coEvery { homeDataApi.fetch(url) } returns Response.success(homeCryptoList)

        // when
        val response = homeRemoteDataSource.fetch(url)

        // then
        val successResponse = response as RepositorySuccess
        assertEquals(homeCryptoList, successResponse.response)
    }

    @Test
    fun GIVEN_success_and_null_data_WHEN_fetch_THEN_return_correct_result() = runTest {
        // given
        val url = "www.exempleurl.fr"
        coEvery { homeDataApi.fetch(url) } returns Response.success(null)

        // when
        val response = homeRemoteDataSource.fetch(url)

        // then
        assertTrue(response is RepositoryFailure)
    }

    @Test
    fun GIVEN_failure_result_WHEN_fetch_THEN_return_correct_result() = runTest {
        // given
        val url = "www.exempleurl.fr"
        coEvery { homeDataApi.fetch(url) } returns Response.error(400, mockk(relaxed = true))

        // when
        val response = homeRemoteDataSource.fetch(url)

        // then
        assertTrue(response is RepositoryFailure)
    }

    @Test
    fun GIVEN_http_exception_result_WHEN_fetch_THEN_return_correct_result() = runTest {
        // given
        val url = "www.exempleurl.fr"
        coEvery { homeDataApi.fetch(url) } coAnswers { throw HttpException(mockk()) }

        // when
        val response = homeRemoteDataSource.fetch(url)

        // then
        assertTrue(response is RepositoryFailure)
    }
}