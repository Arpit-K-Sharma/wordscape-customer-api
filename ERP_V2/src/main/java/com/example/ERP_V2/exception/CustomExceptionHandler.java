package com.example.ERP_V2.exception;

import com.example.ERP_V2.DTO.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<ErrorMessage> userAlreadyExistsException(
//            UserAlreadyExistsException userAlreadyExistsException, WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage(userAlreadyExistsException.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorMessage> emptyResultDataAccessException(
            EmptyResultDataAccessException emptyResultDataAccessException, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(emptyResultDataAccessException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runTimeException(
            RuntimeException runTimeException, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(runTimeException.getMessage());
        logger.error ("Exception occurred: ", runTimeException); // Logging the exception with stack trace
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentException(
            IllegalArgumentException illegalArgumentException, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(illegalArgumentException.getMessage());
        logger.error("Exception occurred: ", illegalArgumentException); // Logging the exception with stack trace
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
