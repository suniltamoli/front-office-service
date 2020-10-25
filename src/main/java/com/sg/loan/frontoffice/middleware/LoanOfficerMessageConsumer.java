package com.sg.loan.frontoffice.middleware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.loan.frontoffice.commons.FrontOfficeException;
import com.sg.loan.frontoffice.model.LoanRequest;
import com.sg.loan.frontoffice.services.CustomerLoanService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LoanOfficerMessageConsumer {
    private final CustomerLoanService customerService;
    private final ObjectMapper objectMapper;

    public LoanOfficerMessageConsumer(CustomerLoanService customerService, ObjectMapper objectMapper) {
        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "${LOAN_OFFICER_RES_TOPIC}")
    public void consumeMessage(String message) throws JsonProcessingException, FrontOfficeException {
        LoanRequest loanRequest = objectMapper.readValue(message, LoanRequest.class);
        customerService.updateRequestForLoanOfficer(loanRequest);
    }
}
