package com.poc.exception

class BadRequestException(
    baseResponseCode: BaseResponseCode,
    override val message: String? = baseResponseCode.message
) : RuntimeException() {
    val baseResponseCode: BaseResponseCode = baseResponseCode
}

