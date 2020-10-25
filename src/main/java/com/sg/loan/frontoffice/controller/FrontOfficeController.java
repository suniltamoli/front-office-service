package com.sg.loan.frontoffice.controller;

import com.sg.loan.frontoffice.commons.FrontOfficeError;
import com.sg.loan.frontoffice.commons.FrontOfficeException;
import com.sg.loan.frontoffice.model.LoanRequest;
import com.sg.loan.frontoffice.services.CustomerLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/carloan")
public class FrontOfficeController {

    private final CustomerLoanService customerService;

    public FrontOfficeController(CustomerLoanService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/approval")
    public ResponseEntity<?> createLoanRequest(@Valid @RequestBody LoanRequest loanRequest) {
        try {
            loanRequest = customerService.approveOrRejectByFrontOfficer(loanRequest);
            if(loanRequest == null) {
                FrontOfficeError frontOfficeError = new FrontOfficeError("10002", "Invalid kyc number");
                return new ResponseEntity<>(frontOfficeError, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            if(e instanceof FrontOfficeException) {
                FrontOfficeError loanError = new FrontOfficeError(((FrontOfficeException) e).getErroCode(), ((FrontOfficeException) e).getMessage());
                return new ResponseEntity<FrontOfficeError>(loanError, HttpStatus.BAD_REQUEST);
            } else {
                FrontOfficeError loanError = new FrontOfficeError(((FrontOfficeException) e).getErroCode(),  e.getMessage());
                return new ResponseEntity<FrontOfficeError>(loanError, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<LoanRequest>(loanRequest, HttpStatus.ACCEPTED);
    }

    @GetMapping("/details/{loanRefNum}")
    public ResponseEntity<?> getLoanStatus(@PathVariable(value = "loanRefNum") String loanRefNum) {
        LoanRequest loanRequest;
        try {
            loanRequest =  customerService.getLoanDetails(loanRefNum);
        } catch (Exception e) {
            if(e instanceof FrontOfficeException) {
                FrontOfficeError loanError = new FrontOfficeError(((FrontOfficeException) e).getErroCode(), ((FrontOfficeException) e).getMessage());
                return new ResponseEntity<FrontOfficeError>(loanError, HttpStatus.BAD_REQUEST);
            } else {
                FrontOfficeError loanError = new FrontOfficeError(((FrontOfficeException) e).getErroCode(),  e.getMessage());
                return new ResponseEntity<FrontOfficeError>(loanError, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<LoanRequest>(loanRequest, HttpStatus.OK);
    }
}
