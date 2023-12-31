package com.zhouyi.ddd.infrastructure.exception;

import com.zhouyi.ddd.infrastructure.model.result.Result;
import com.zhouyi.ddd.infrastructure.model.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;

/**
 * 拦截控制层异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 检查参数个数和类型异常
     * @param ex ex

     * @date 2021/1/12 17:15

     */
    @ExceptionHandler(BindException.class)
    public Result paramBindException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        if (fieldErrors == null || fieldErrors.size() <= 0 || Validator.isEmpty(fieldErrors.get(0).getDefaultMessage())) {
            return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ResultCodeEnum.PARAM_VALID_ERROR.getMessage(), null);
        }
        return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), fieldErrors.get(0).getDefaultMessage(), null);
    }



    /**
     * 检查单个参数类型异常
     * @param ex ex

     * @date 2021/1/19 15:57

     */
    @ExceptionHandler({ValidationException.class})
    public Result paramValidationException(ValidationException ex) {
        if (ex == null || Validator.isEmpty(ex.getMessage())) {
            return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ResultCodeEnum.PARAM_VALID_ERROR.getMessage(), null);
        }
        return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ex.getMessage(), null);
    }

    /**
     * 拦截@RequestParam参数未传入异常
     * @param ex ex
     * @author wm
     * @date 2019/9/11 11:12
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        if (ex.getMessage() == null || Validator.isEmpty(ex.getMessage())) {
            return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ResultCodeEnum.PARAM_VALID_ERROR.getMessage(), null);
        }
        return new Result<>(true, ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ex.getMessage(), null);
    }


    /**
     * 处理所有的业务异常
     * @param ex ex
     */
    @ExceptionHandler(Exception.class)
    public Result otherException(Exception ex) {
        ex.printStackTrace();

        return new Result<>(true, ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ResultCodeEnum.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    @ExceptionHandler(ServiceException.class)
    public Result exception(ServiceException e) {
        log.error(e.getMessage(), e);
        return new Result<>(false,e.getCode(),e.getMessage(),null );
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exception(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        ObjectError firstError = errors.stream().findFirst().get();
        return new Result<>(false, ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), firstError.getDefaultMessage(), null);
    }

    //参数类型不匹配
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);

        return new Result<>(false, ResultCodeEnum.PARAM_VALID_ERROR.getCode(),"参数类型不匹配", null);
    }



}
