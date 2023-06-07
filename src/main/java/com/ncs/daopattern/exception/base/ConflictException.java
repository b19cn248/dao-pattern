package com.ncs.daopattern.exception.base;

import com.ncs.daopattern.exception.base.BaseException;

public class ConflictException extends BaseException {
    public ConflictException(String objectName) {
        setCode("com.ncsgroup.ntp.exception.base.ConflictException");
        setStatus(409); // const = CONFILCT
        addParam("objectName", objectName);
    }
}
