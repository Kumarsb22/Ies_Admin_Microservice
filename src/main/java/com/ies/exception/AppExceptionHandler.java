package com.ies.exception;
import com.ies.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

Logger logger= LoggerFactory.getLogger(AppExceptionHandler.class);

@ExceptionHandler(value = Exception.class)
public ResponseEntity<AppException> handleException(String exMsg){
    logger.error(AppConstants.STR_EXCEP_OCCURRED + exMsg);
    AppException appException = new AppException();
    appException.setExceptCode(AppConstants.STR_EX_CODE);
    appException.setExceptDesc(exMsg);
    appException.setExceptDate(LocalDateTime.now());
    return new ResponseEntity<AppException>(appException, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
