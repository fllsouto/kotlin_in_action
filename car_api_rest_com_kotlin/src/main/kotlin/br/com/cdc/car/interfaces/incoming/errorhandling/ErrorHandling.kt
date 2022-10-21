package br.com.cdc.car.interfaces.incoming.errorhandling

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest

data class ErrorData(
    val message: String
)

data class ErrorResponse(
    val errors: List<ErrorData>
)

@RestControllerAdvice
class DefaultErrorHandler(
    val messageSource: MessageSource
) {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleArgumentNotValid(ex: MethodArgumentNotValidException): ErrorResponse {
        val messages = ex.bindingResult.fieldErrors.map {
            getMessage(it)
        }
        return ErrorResponse(messages)
    }

    private fun getMessage(it: FieldError): ErrorData {
        val message = messageSource.getMessage(it, LocaleContextHolder.getLocale())
        return ErrorData(message)
    }
}

@Component
class LocaleResolver: AcceptHeaderLocaleResolver() {
    private val DEFAULT_LOCALE = Locale("pt", "BR")
    private val ACCEPTED_LOCALES = listOf(DEFAULT_LOCALE, Locale("en"))

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val acceptLanguageHeader = request.getHeader("Accept-Language")
        if (acceptLanguageHeader.isNullOrEmpty() || acceptLanguageHeader.trim() == "*") {
            return DEFAULT_LOCALE
        }
        val list = Locale.LanguageRange.parse(acceptLanguageHeader)
        return Locale.lookup(list, ACCEPTED_LOCALES) ?: DEFAULT_LOCALE
    }
}