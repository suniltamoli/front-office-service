package com.sg.loan.frontoffice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.loan.frontoffice.commons.FrontOfficeException;
import com.sg.loan.frontoffice.commons.Validator;
import com.sg.loan.frontoffice.entity.LoanRequestEntity;
import com.sg.loan.frontoffice.middleware.MessageProducer;
import com.sg.loan.frontoffice.model.CustomerDetails;
import com.sg.loan.frontoffice.model.LoanRequest;
import com.sg.loan.frontoffice.repository.CustomerLoanRepository;
import com.sg.loan.frontoffice.transformer.LoanRequestTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.sql.Timestamp;

@Slf4j
@Service
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
    private CustomerLoanRepository customerRepository;
    private LoanRequestTransformer transformer;
    private Validator validator;
    private final MessageProducer messageProducer;
    private final ObjectMapper objectMapper;

    public CustomerLoanServiceImpl(CustomerLoanRepository customerRepository, LoanRequestTransformer transformer, Validator validator, MessageProducer messageProducer, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.transformer = transformer;
        this.validator = validator;
        this.messageProducer = messageProducer;
        this.objectMapper = objectMapper;
    }

    @Override
    public LoanRequest approveOrRejectByFrontOfficer(LoanRequest loanRequest) throws FrontOfficeException , RestClientException {
        try {
            // may be call some other third party api before approving loan request

            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "http://localhost:" + 8080 + "/kyc-service/v1/carloan/customer/kyc/"+loanRequest.getKycNumber();
            URI uri = new URI(baseUrl);
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            if(result.getBody() == null) {
                return null;
            }
            CustomerDetails customerDetails = objectMapper.readValue(result.getBody(), CustomerDetails.class);
            if(!customerDetails.getKyc_number() .equals(loanRequest.getKycNumber())) {
                return null;
            }
            if(loanRequest.getAnnualSalary() >=5000000 && "4 wheeler".equalsIgnoreCase(loanRequest.getVehicleType() )){
                loanRequest.setApprovedByFrontOfficer("front officer user id");
            }
            validator.validateRequest(loanRequest);
            LoanRequestEntity loanRequestEntity = customerRepository.save(transformer.transformCustomerDetails(loanRequest));
            loanRequest.setLoanRefNumber(loanRequestEntity.getLoanRefNumber());
            loanRequest.setApprovedByFrontOfficer(loanRequestEntity.getApprovedByFrontOfficer());
            loanRequest.setApprovedOnFrontOfficer(loanRequestEntity.getApprovedOnFrontOfficer());
            messageProducer.publishMessage(loanRequest);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                log.error(e.getMessage());
                throw new FrontOfficeException(e.getMessage(), "200001");
            }
        }
        return loanRequest;
    }

    @Override
    public LoanRequest updateRequestForLoanOfficer(LoanRequest loanRequest) throws FrontOfficeException {
        Timestamp approvedOnLoanOfficer = new Timestamp(System.currentTimeMillis());
        String approvedBy = "loan-officer";
        customerRepository.updateByLoanofficer(approvedBy, approvedOnLoanOfficer,  loanRequest.getLoanStatus(), loanRequest.getLoanRefNumber());
        loanRequest.setApprovedByLoanOfficer(approvedBy);
        loanRequest.setApprovedOnLoanOfficer(approvedOnLoanOfficer);
        return loanRequest;
    }

    @Override
    public LoanRequest updateRequestForRiskOfficer(LoanRequest loanRequest) throws FrontOfficeException {
        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date parsedDate = dateFormat.parse(loanRequest.getApprovedOnRiskOfficer());
//            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            String approvedBy = loanRequest.getApprovedByRiskOfficer();
            customerRepository.updateByRiskofficer(approvedBy, loanRequest.getApprovedOnRiskOfficer()  ,  loanRequest.getLoanStatus(),loanRequest.getLoanRefNumber() );

        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }

        return loanRequest;
    }


    @Override
    public LoanRequest getLoanDetails(String loanRefNumber) throws FrontOfficeException {
        LoanRequest loanRequest = new LoanRequest();
        BeanUtils.copyProperties( customerRepository.getLoanDetailsByloanRefNumber(loanRefNumber) ,loanRequest);
        return loanRequest;
    }

}
