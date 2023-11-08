package com.item.Exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionCustom extends Exception {
    private Integer exception_id;

    public ExceptionCustom() {

    }

    public Integer getException_id() {
        return exception_id;
    }

    public void setException_id(Integer exception_id) {
        this.exception_id = exception_id;
    }
}
