package com.compose.data.home.repositroy

import com.compose.business.common.model.AppError
import com.compose.data.common.RepositoryFailure
import com.compose.data.common.RepositorySuccess
import com.compose.data.home.datasource.HomeLocalDataSource
import com.compose.data.home.datasource.HomeRemoteDataSource
import com.compose.data.home.testmodels.HomeCryptoModelTestProvider
import com.compose.data.remoteconfig.RemoteConfigRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class HomeRepositoryTest {

    private val homeRemoteDataSource: HomeRemoteDataSource = mockk()
    private val homeLocalDataSource: HomeLocalDataSource = mockk()
    private val remoteConfigRepository: RemoteConfigRepository = mockk()

    private val homeRepository =
        HomeRepository(homeRemoteDataSource, homeLocalDataSource, remoteConfigRepository)

    private val provider = HomeCryptoModelTestProvider()

    @Test
    fun GIVEN_localData_WHEN_fetchHomeData_THEN_return_correct_result() = runTest {
        // given
        val localData = provider.getHomeCryptoTestModels(5, false)
        every { homeLocalDataSource.data } returns localData

        // when
        val repositoryResponse = homeRepository.fetchHomeData()

        // then
        val successResponse = repositoryResponse as RepositorySuccess
        assertEquals(localData, successResponse.response)
        coVerify(exactly = 0) { homeRemoteDataSource.fetch(any()) }
        coVerify(exactly = 0) { homeLocalDataSource.cacheResponse(any()) }
    }

    @Test
    fun GIVEN_noLocalData_and_success_WHEN_fetchHomeData_THEN_return_correct_result() = runTest {
        // given
        val url = "www.test.fr"
        every { remoteConfigRepository.cryptoDataUrl } returns url
        val remoteResponse = provider.getHomeCryptoTestModels(5, false)
        every { homeLocalDataSource.data } returns null
        every { homeLocalDataSource.cacheResponse(any()) } returns Unit
        coEvery { homeRemoteDataSource.fetch(any()) } coAnswers { RepositorySuccess(remoteResponse) }

        // when
        val repositoryResponse = homeRepository.fetchHomeData()

        // then
        val successResponse = repositoryResponse as RepositorySuccess
        assertEquals(remoteResponse, successResponse.response)
        coVerify(exactly = 1) { homeRemoteDataSource.fetch(url) }
        coVerify(exactly = 1) { homeLocalDataSource.cacheResponse(successResponse) }
    }

    @Test
    fun GIVEN_noLocalData_and_failure_WHEN_fetchHomeData_THEN_return_correct_result() = runTest {
        // given
        val url = "www.test.fr"
        every { remoteConfigRepository.cryptoDataUrl } returns url
        every { homeLocalDataSource.data } returns null
        every { homeLocalDataSource.cacheResponse(any()) } returns Unit
        coEvery { homeRemoteDataSource.fetch(any()) } coAnswers { RepositoryFailure(AppError.EMPTY_RESPONSE) }

        // when
        val repositoryResponse = homeRepository.fetchHomeData()

        // then
        assertTrue(repositoryResponse is RepositoryFailure)
        assertEquals(AppError.EMPTY_RESPONSE, (repositoryResponse as RepositoryFailure).error)
        coVerify(exactly = 1) { homeRemoteDataSource.fetch(url) }
        coVerify(exactly = 1) { homeLocalDataSource.cacheResponse(repositoryResponse) }
    }
}