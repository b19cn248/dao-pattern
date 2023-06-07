package com.ncs.daopattern.exception.base;

import com.ncs.daopattern.exception.base.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String id, String objectName) {
        setCode("com.ncsgroup.ntp.exception.base.NotFoundException");
        setStatus(404);
        addParam("id", id);
        addParam("objectName", objectName);
    }

    public NotFoundException(String id) {
        setCode("com.ncsgroup.ntp.exception.base.NotFoundException");
        setStatus(404);
        addParam("id", id);
    }

    public NotFoundException() {
        setCode("com.ncsgroup.ntp.exception.base.NotFoundException");
        setStatus(404);
    }
}
