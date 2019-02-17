package com.doraro.exception;


import com.doraro.exception.beans.ApiResponses;
import com.doraro.exception.beans.ErrorCode;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.utils.ValidationUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<ApiResponses> invalidInput(MethodArgumentNotValidException ex) {
        final ErrorCodeEnum badRequest = ErrorCodeEnum.BAD_REQUEST;
        final ApiResponses responses = ApiResponses.failure(badRequest,
                ValidationUtil.fromBindingErrors(ex.getBindingResult()));
        return new ResponseEntity<>(responses, badRequest.getHttpStatus());
    }

    /**
     * 参数值非法异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponses> invalidInput(HttpMessageNotReadableException ex) {
        ErrorCodeEnum error;
        if (ex.getCause() instanceof JsonParseException) {
            error = ErrorCodeEnum.JSON_FORMAT_ERROR;
        } else {
            error = ErrorCodeEnum.BAD_REQUEST;
        }
        return sendResponseEntity(error, ex.getMessage());
    }


    /**
     * 处理业务逻辑异常
     */
    @ExceptionHandler(ApiGlobalException.class)
    public ResponseEntity<ApiResponses> resourcesNotFound(ApiGlobalException ex) {
        final ErrorCode badRequest = ex.getErrorCode();
        final ApiResponses responses = ApiResponses.failure(badRequest, ex.getMessage());
        return new ResponseEntity<>(responses, HttpStatus.valueOf(badRequest.getHttpCode()));
    }

    /**
     * 上传文件异常
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiResponses> handleFileError(MultipartException ex) {
        final ErrorCodeEnum badRequest = ErrorCodeEnum.FILE_UPLOAD_FAIL;
        return sendResponseEntity(badRequest, ex.getMessage());
    }

    /**
     * 处理七牛上传图片的异常
     */
    @ExceptionHandler(QiniuException.class)
    public ResponseEntity<ApiResponses> handleQiNiuUpLoad(QiniuException ex) {
        Response r = ex.response;
        log.info("reason: {}", r.toString());
        try {
            log.info(r.bodyString());
        } catch (QiniuException ex2) {
            //ignore
        }
        final ErrorCodeEnum badRequest = ErrorCodeEnum.FILE_UPLOAD_FAIL;
        return sendResponseEntity(badRequest, ex.getMessage());
    }


    private ResponseEntity<ApiResponses> sendResponseEntity(ErrorCodeEnum error, String message) {
        final ApiResponses responses = ApiResponses.failure(error, message);
        return new ResponseEntity<>(responses, error.getHttpStatus());
    }
}




