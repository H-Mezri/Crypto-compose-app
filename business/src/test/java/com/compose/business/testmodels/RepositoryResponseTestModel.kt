package com.compose.business.testmodels

import com.compose.business.home.repository.RepositoryFailureInterface
import com.compose.business.home.repository.RepositorySuccessInterface

class RepositorySuccessTestModel<T>(override val response: T) : RepositorySuccessInterface<T>
class RepositoryFailureTestModel<T> : RepositoryFailureInterface<T>