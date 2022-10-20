package br.com.cdc.car.interfaces.incoming.errorhandling

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorData(
    val message: String
)

data class ErrorResponse(
    val errors: List<ErrorData>
)

@RestControllerAdvice
class DefaultErrorHandler {
    @ExceptionHandler
    fun handleArgumentNotValid(ex: MethodArgumentNotValidException): ErrorResponse {
        val messages = ex.bindingResult.fieldErrors.map {
            ErrorData(it.defaultMessage!!)
        }
        return ErrorResponse(messages)
    }
}