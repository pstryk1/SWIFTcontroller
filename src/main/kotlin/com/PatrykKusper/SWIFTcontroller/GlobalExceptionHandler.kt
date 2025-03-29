package com.PatrykKusper.SWIFTcontroller

import com.PatrykKusper.SWIFTcontroller.Services.BadRequestException
import com.PatrykKusper.SWIFTcontroller.Services.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(exception: NotFoundException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(mapOf("error" to exception.message!!), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(exception: BadRequestException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(mapOf("error" to exception.message!!), HttpStatus.BAD_REQUEST)
    }
}