package com.doraro.exception;


import com.doraro.exception.beans.ApiResponses;
import com.doraro.exception.beans.ErrorCode;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.utils.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;


/**
 * Created by cyheng on 2018/2/21.
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    /**
     * 处理不符合@Vaild 所抛出的异常
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponses invalidInput(MethodArgumentNotValidException ex) {
        return ApiResponses.failure(ErrorCodeEnum.BAD_REQUEST.getHttpCode(),
                ValidationUtil.fromBindingErrors(ex.getBindingResult()));
    }

    /**
     * 缺少参数处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponses missParamException(MissingServletRequestParameterException ex) {
        final ErrorCodeEnum badRequest = ErrorCodeEnum.BAD_REQUEST;
        return ApiResponses.failure(badRequest.convert(ex.getMessage()));
    }



    /**
     * 参数值非法异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponses invalidInput(HttpMessageNotReadableException ex) {
        ErrorCodeEnum error;
        if (ex.getCause() instanceof JsonProcessingException) {
            error = ErrorCodeEnum.JSON_FORMAT_ERROR;
        } else {
            error = ErrorCodeEnum.BAD_REQUEST;
        }
        return ApiResponses.failure(error);
    }

    /**
     * shiro 权限异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ApiResponses handleAuthorizationException(AuthorizationException e) {
        return ApiResponses.failure(ErrorCodeEnum.FORBIDDEN);
    }
    /**
     * 处理业务逻辑异常
     */
    @ExceptionHandler(ApiGlobalException.class)
    public ApiResponses resourcesNotFound(ApiGlobalException ex) {
        final ErrorCode badRequest = ex.getErrorCode();
        return ApiResponses.failure(badRequest);
    }

    /**
     * 上传文件异常
     */
    @ExceptionHandler(MultipartException.class)
    public ApiResponses handleFileError(MultipartException ex) {
        final ErrorCodeEnum badRequest = ErrorCodeEnum.FILE_UPLOAD_FAIL;
        return ApiResponses.failure(badRequest);
    }

    /**
     * 处理七牛上传图片的异常
     */
    @ExceptionHandler(QiniuException.class)
    public ApiResponses handleQiNiuUpLoad(QiniuException ex) {
        Response r = ex.response;
        log.info("reason: {}", r.toString());
        try {
            log.info(r.bodyString());
        } catch (QiniuException ex2) {
            //ignore
        }
        final ErrorCodeEnum badRequest = ErrorCodeEnum.FILE_UPLOAD_FAIL;
        return ApiResponses.failure(badRequest);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponses unknownException(Exception ex) {
        return ApiResponses.failure(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }

}




