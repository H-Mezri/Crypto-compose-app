package com.compose.business.home.repository

import com.compose.business.common.model.AppError

sealed class RepositoryResponse<T>
class RepositorySuccess<T>(val response: T) : RepositoryResponse<T>()
class RepositoryFailure<T>(val appError: AppError) : RepositoryResponse<T>()