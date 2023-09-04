package com.compose.data.common

import com.compose.business.common.model.AppError
import com.compose.business.home.repository.RepositoryFailureInterface
import com.compose.business.home.repository.RepositorySuccessInterface

class RepositorySuccess<T>(override val response: T) : RepositorySuccessInterface<T>
class RepositoryFailure<T>(val error: AppError) : RepositoryFailureInterface<T>