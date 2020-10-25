package com.sg.loan.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoanRequest implements Serializable {
    @JsonProperty("loan_ref_number")
    private String loanRefNumber;
    @JsonProperty("pan_number")
    private String panNumber;
    @JsonProperty("vehicle_company")
    private String vehicleCompany;
    @JsonProperty("vehicle_name")
    private String vehicleName;
    @JsonProperty("vehicle_type")
    private String vehicleType;
    @JsonProperty("total_price")
    private Long totalPrice;
    @JsonProperty("loan_amount")
    private Long loanAmount;
    @JsonProperty("down_payment")
    private Long downPayment;
    @JsonProperty("roi")
    private Double roi;
    @JsonProperty("financed_by")
    private String financedBy;
    @JsonProperty("financer_address")
    private String financerAddress;
    @JsonProperty("tenure")
    private Integer tenure;
    @JsonProperty("emi")
    private Integer emi;
    @JsonProperty("kyc_number")
    private String kycNumber;
    @JsonProperty("loan_status")
    private String loanStatus;
    @JsonProperty("annual_salary")
    private Long annualSalary;
    @JsonProperty("form_16_received")
    private Boolean form16Received;
    @JsonProperty("bank_statement_received")
    private Boolean bankStatementReceived;
    @JsonProperty("approved_by_loan_officer")
    private String approvedByLoanOfficer;
    @JsonProperty("approved_by_risk_officer")
    private String approvedByRiskOfficer;
    @JsonProperty("approved_by_front_officer")
    private String approvedByFrontOfficer;
    @JsonProperty("approved_On_by_front_officer")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="Europe/Berlin")
    private Timestamp approvedOnFrontOfficer;
    @JsonProperty("approved_On_by_risk_officer")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="Europe/Berlin")
    private Timestamp approvedOnRiskOfficer;
    @JsonProperty("approved_On_by_loan_officer")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="Europe/Berlin")
    private Timestamp approvedOnLoanOfficer;
    private final static long serialVersionUID = 3343543324008830673L;
}
