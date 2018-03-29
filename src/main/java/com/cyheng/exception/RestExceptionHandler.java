package com.cyheng.exception;


import com.cyheng.model.VO.ResultBean;
import com.cyheng.utils.ValidationUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import static com.cyheng.model.VO.ResultBean.STATUS.*;

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
    public ResponseEntity<ResultBean> invalidInput(MethodArgumentNotValidException ex) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage("Invalid inputs");
        resultBean.setCode(VALIDATIONERROR);
        resultBean.setData(ValidationUtil.fromBindingErrors(ex.getBindingResult()));
        return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ResultBean> resourcesNotFound(ResourcesNotFoundException ex) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage(ex.getMessage());
        resultBean.setCode(NOTFOUND);
        return new ResponseEntity<>(resultBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<ResultBean> resourcesNotFound(CheckException ex) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage(ex.getMessage());
        resultBean.setCode(VALIDATIONERROR);
        return new ResponseEntity<>(resultBean, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ResultBean> handleFileError(MultipartException ex) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage(ex.getMessage());
        resultBean.setCode(FAIL);
        return new ResponseEntity<>(resultBean, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(QiniuException.class)
    public ResponseEntity<ResultBean> handleQiNiuUpLoad(QiniuException ex) {
        Response r = ex.response;
        log.info("reason: {}", r.toString());
        try {
            log.info(r.bodyString());
        } catch (QiniuException ex2) {
            //ignore
        }
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage(ex.getMessage());
        resultBean.setCode(FAIL);
        return new ResponseEntity<>(resultBean, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}




