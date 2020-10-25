package com.sg.loan.frontoffice.commons;

import lombok.Data;

@Data
public class FrontOfficeException extends Exception {
    private final String message;
    private final String erroCode;

    public FrontOfficeException(String message, String erroCode) {
        super(message);
        this.message = message;
        this.erroCode = erroCode;
    }
}
