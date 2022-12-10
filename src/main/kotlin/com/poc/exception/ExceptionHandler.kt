package com.poc.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.io.PrintWriter
import java.io.StringWriter

@RestControllerAdvice
class ExceptionHandler {

    private val logger = KotlinLogging.logger{}

    private fun log(ex: Exception) {
        val message: String = getStackStringFromException(ex)
        logger.error("[ERROR] $message")

    }

    private fun getStackStringFromException(ex: java.lang.Exception): String {
        val errors = StringWriter()
        ex.printStackTrace(PrintWriter(errors))
        return errors.toString()
    }

    // 유효성 오류 및 확인된 예외 (400)
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun badRequestException(e: BadRequestException): ResponseEntity<String?>? {
        log(e)
        return ResponseEntity.status(e.baseResponseCode.status)
            .body(e.message)
    }
}