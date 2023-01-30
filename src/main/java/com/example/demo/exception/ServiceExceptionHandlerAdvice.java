package com.example.demo.exception;

import com.example.demo.common.constants.ExceptionEnumConstant;
import com.example.demo.common.response.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandlerAdvice {

    /**
     * 业务异常
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object serviceException(ServiceException exception) {
        return ResponseGenerator.genFailResult(exception.getCode(),exception.toString());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object badRequestException(IllegalArgumentException exception) {
        log.error("[IllegalArgumentException] => ", exception);
        return ResponseGenerator.genFailResult(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
    }

    /**
     * 数据库注入拦截
     */
    @ExceptionHandler({SQLInjectionException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object sqlInjectionException(Exception e) {
        return ResponseGenerator.genFailResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    /**
     * 拦截MethodArgumentNotValidException异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, org.springframework.validation.BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object notValid(Exception e) {
        log.error("[MethodArgumentNotValidException],[BindException] => ", e);
        try {
            if (e instanceof MethodArgumentNotValidException) {
                return ResponseGenerator.genFailResult(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                        ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().get(0).getField() + ":" +
                                ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().get(0).getDefaultMessage());
            } else if (e instanceof org.springframework.validation.BindException) {
                org.springframework.validation.BindException ex = (org.springframework.validation.BindException) e;
                List<ObjectError> errors = ex.getAllErrors();
                ObjectError error = errors.get(0);
                String msg = "";
                /*if (Objects.requireNonNull(error.getArguments()).length > 0) {
                    msg += ((DefaultMessageSourceResolvable) (error.getArguments()[0])).getDefaultMessage();
                }*/
                msg += error.getDefaultMessage();

                return ResponseGenerator.genFailResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
            }
        } catch (Exception el) {
            return ResponseGenerator.genFailResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return ResponseGenerator.genFailResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object notFount(RuntimeException e) {
        log.error("[RuntimeException] => ", e);
        return ResponseGenerator.genFailResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionEnumConstant.RUNTIME_EXCEPTION_ERROR.getMessage());
    }
}
