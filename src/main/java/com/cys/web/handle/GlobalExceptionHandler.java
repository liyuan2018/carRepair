package com.cys.web.handle;

import com.cys.common.domain.ErrorMessage;
import com.cys.exception.*;
import com.cys.util.I18nUtils;
import com.cys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

/**
 * Created by liyuan on 2018/3/16.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private String appNo = "000-";

    public GlobalExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ErrorMessage handleAllException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-100", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BusinessException.class})
    public ErrorMessage handleBusinessException(BusinessException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        String errorCode = this.appNo + ex.getType() + "101";
        if(!StringUtils.isEmpty(ex.getErrorCode())) {
            errorCode = this.appNo + ex.getType() + ex.getErrorCode().getCode();
        }

        return !StringUtils.isEmpty(ex.getData())?new ErrorMessage(errorCode, ex.getMsg(), ex.getData()):new ErrorMessage(errorCode, ex.getMsg(), ex.getSuperMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ErrorMessage handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-102", I18nUtils.getMessage("Common.resourceNotFound"), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SystemException.class})
    public ErrorMessage handleSystemException(SystemException ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-103", ex.getMsg(), ex.getSuperMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MultipartException.class})
    public ErrorMessage handleMultipartException(MultipartException ex) {
        logger.error(ex.getMessage(), ex);
        return ex.getMessage().contains("SizeLimitExceededException")?new ErrorMessage(this.appNo + "B-104", I18nUtils.getMessage("Common.fileSizeNotAllow"), ex.getMessage()):new ErrorMessage(this.appNo + "B-104", I18nUtils.getMessage("Common.uploadFailed"), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataAccessException.class})
    public ErrorMessage handleDataAccessException(DataAccessException ex) {
        logger.error(ex.getMessage(), ex);
        String errorCode = this.appNo + "B-105";
        return new ErrorMessage(errorCode, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({SessionTimeoutException.class})
    public ErrorMessage handleSessionTimeoutSystemException(SessionTimeoutException ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-106", ex.getMsg());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-107", ex.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-108", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-109", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ErrorMessage handleNotFoundException(ResourceNotFoundException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-110", ex.getMsg());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceIdentifyNotConfigException.class})
    public ErrorMessage handleResourceIdentifyNotConfigException(Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        return new ErrorMessage(this.appNo + "B-111", ex.getMessage());
    }
}
