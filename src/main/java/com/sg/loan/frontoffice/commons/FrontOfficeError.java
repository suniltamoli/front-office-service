package com.sg.loan.frontoffice.commons;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontOfficeError implements Serializable {
    private final String errorCode;
    private final String errorMessage;

    public FrontOfficeError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
