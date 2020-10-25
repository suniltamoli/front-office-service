package com.sg.loan.frontoffice.transformer;

import com.sg.loan.frontoffice.entity.LoanRequestEntity;
import com.sg.loan.frontoffice.model.LoanRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class LoanRequestTransformer {
    public LoanRequestEntity transformCustomerDetails(LoanRequest loanRequest) {
        LoanRequestEntity loanRequestEntity = new LoanRequestEntity();
        BeanUtils.copyProperties(loanRequest, loanRequestEntity);
        loanRequestEntity.setLoanRefNumber("REF"+UUID.randomUUID().toString().replace("-", ""));
        loanRequestEntity.setApprovedOnFrontOfficer(new Timestamp(System.currentTimeMillis()));
        loanRequestEntity.setApprovedByFrontOfficer("su007ta");
        return  loanRequestEntity;
    }
}
