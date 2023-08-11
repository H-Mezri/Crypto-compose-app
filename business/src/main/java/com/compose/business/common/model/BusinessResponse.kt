package com.compose.business.common.model

sealed class BusinessResponse<T>
class Success<T>(val result: T): BusinessResponse<T>()
class Failure<T>(val result: AppError): BusinessResponse<T>()

