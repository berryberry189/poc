package com.poc.exception

import org.springframework.http.HttpStatus

enum class BaseResponseCode (status: HttpStatus, message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    ERR_KEY_ARG_IS_WRONG(HttpStatus.NOT_FOUND, "존재하지 않는 상세 키 입니다.");

    val status: HttpStatus = status
    val message: String = message
}
