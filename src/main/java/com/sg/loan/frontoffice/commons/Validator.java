package com.sg.loan.frontoffice.commons;

import com.sg.loan.frontoffice.model.LoanRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateRequest(LoanRequest loanRequest) throws FrontOfficeException {

        if(StringUtils.isBlank(loanRequest.getKycNumber())) {
            throw new FrontOfficeException("KYC Number can not be null, Please do your KYC first.", "300001");
        }

        if(StringUtils.isBlank(loanRequest.getFinancedBy())) {
            throw new FrontOfficeException("Financer can not be null", "300002");
        }

        if(StringUtils.isBlank(loanRequest.getFinancerAddress())) {
            throw new FrontOfficeException("Financer address can not be null", "300003");
        }

        if(StringUtils.isBlank(loanRequest.getPanNumber())) {
            throw new FrontOfficeException("PAN Number can not be null", "300004");
        }

        if(StringUtils.isBlank(loanRequest.getVehicleCompany())) {
            throw new FrontOfficeException("Vehicle company name can not be null", "300005");
        }

        if(StringUtils.isBlank(loanRequest.getVehicleName())) {
            throw new FrontOfficeException("Vehicle name can not be null", "300006");
        }

        if(StringUtils.isBlank(loanRequest.getVehicleType())) {
            throw new FrontOfficeException("Vehicle type can not be null", "300007");
        }

        if(loanRequest.getAnnualSalary() <=0) {
            throw new FrontOfficeException("Annual salary can not be less than or equal 0", "300008");
        }


    }

}
