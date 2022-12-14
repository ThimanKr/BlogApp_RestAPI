package com.springboot.blog.exception;

import com.springboot.blog.controller.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** The GlobalExceptionHandler is the exception handle used for validation exception handling */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method is to handle ResourceNotFoundException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){

            ErrorDetails errorDetails = ErrorDetails.builder()
                    .timestamp(new Date())
                    .message(exception.getMessage())
                    .errorDetails(request.getDescription(false))
                    .build();

            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This method is to handle APIException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleAPIException(APIException exception, WebRequest request){

        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .errorDetails(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is to handle MethodArgumentNotValidException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        // store all the validations errors to a map with property
        Map<String, String> errors = new HashMap<>();

        // extract each err against property and store it to map
        ex.getBindingResult().getAllErrors().forEach(err -> {
            // get the property name from err.
            String propertyName = ((FieldError)err).getField();
            // get the default msg set in validation.
            String errorMessage = err.getDefaultMessage();

            errors.put(propertyName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is to handle global exception
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request){

        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .errorDetails(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
