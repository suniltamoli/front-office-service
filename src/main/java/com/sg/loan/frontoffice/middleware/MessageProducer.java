package com.sg.loan.frontoffice.middleware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.loan.frontoffice.model.LoanRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final KafkaTemplate kafkaTemplate;
    private final String loanOfficerTopic;
    private final String riskOfficerTopic;
    private final ObjectMapper objectMapper;

    public MessageProducer(KafkaTemplate kafkaTemplate, @Value("${LOAN_OFFICER_TOPIC}") String loanOfficerTopic, @Value("${RISK_OFFICER_TOPIC}") String riskOfficerTopic, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.riskOfficerTopic = riskOfficerTopic;
        this.loanOfficerTopic = loanOfficerTopic;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(LoanRequest loanRequest)  {
        try {
            String requestAsString = objectMapper.writeValueAsString(loanRequest);
            kafkaTemplate.send(riskOfficerTopic, requestAsString);
            kafkaTemplate.send(loanOfficerTopic, requestAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
