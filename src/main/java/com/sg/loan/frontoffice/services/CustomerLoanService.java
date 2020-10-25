package com.sg.loan.frontoffice.services;

import com.sg.loan.frontoffice.commons.FrontOfficeException;
import com.sg.loan.frontoffice.model.LoanRequest;

public interface CustomerLoanService {
    LoanRequest approveOrRejectByFrontOfficer(LoanRequest loanRequest) throws FrontOfficeException;
    LoanRequest updateRequestForLoanOfficer(LoanRequest loanRequest) throws FrontOfficeException;
    LoanRequest updateRequestForRiskOfficer(LoanRequest loanRequest) throws FrontOfficeException;
    LoanRequest getLoanDetails(String loanRefNumber) throws FrontOfficeException;
}
