package com.compose.business.home.repository

sealed interface RepositoryResponseInterface<T>
interface RepositorySuccessInterface<T> : RepositoryResponseInterface<T> {
    val response: T
}

interface RepositoryFailureInterface<T> : RepositoryResponseInterface<T>