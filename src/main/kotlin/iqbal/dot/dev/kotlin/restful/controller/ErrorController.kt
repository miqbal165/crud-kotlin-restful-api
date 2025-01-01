package iqbal.dot.dev.kotlin.restful.controller

import iqbal.dot.dev.kotlin.restful.exception.NotFoundException
import iqbal.dot.dev.kotlin.restful.exception.UnauthorizedException
import iqbal.dot.dev.kotlin.restful.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validationHandler(methodArgumentNotValidException: MethodArgumentNotValidException):
            ResponseEntity<ErrorResponse> {

        val errors = mutableMapOf<String, String>()

        methodArgumentNotValidException.bindingResult.allErrors.forEach {
            val fieldName = (it as FieldError).field
            val message = it.defaultMessage ?: ""
            errors += fieldName to message
        }

        return ResponseEntity(
            ErrorResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                status = "BAD REQUEST",
                message = errors
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun resourceNotFoundHandler(notFoundException: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = HttpStatus.NOT_FOUND.value(),
                status = "NOT FOUND",
                message = notFoundException.localizedMessage
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(unauthorized: UnauthorizedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                code = HttpStatus.UNAUTHORIZED.value(),
                status = "UNAUTHORIZED",
                message = "Please put your X-Api-Key",
            ),
            HttpStatus.UNAUTHORIZED
        )
    }
}