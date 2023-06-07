package com.ncs.daopattern.exception.base;

public class BadRequestException extends BaseException {
    public BadRequestException() {
        setCode("com.ncsgroup.ntp.exception.base.BadRequestException");
        setStatus(400);
    }
}
