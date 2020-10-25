package com.sg.loan.frontoffice.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "customer_loan", indexes = {@Index(name = "loan_ref_number_index",  columnList="loan_ref_number", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class LoanRequestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="loan_ref_number", nullable=false, length = 50)
    private String loanRefNumber;
    @Column(name="pan_number", nullable=false, length = 10)
    private String panNumber;
    @Column(name="vehicle_company", nullable=false, length = 100)
    private String vehicleCompany;
    @Column(name="vehicle_name", nullable=false, length = 50)
    private String vehicleName;
    @Column(name="vehicle_type", nullable=false, length = 30)
    private String vehicleType;
    @Column(name="total_price", nullable=false)
    private Long totalPrice;
    @Column(name="loan_amount", nullable=false)
    private Long loanAmount;
    @Column(name="down_payment", nullable=false)
    private Long downPayment;
    @Column(name="roi", nullable=false)
    private Double roi;
    @Column(name="financed_by", nullable=false, length = 50)
    private String financedBy;
    @Column(name="financer_address", nullable=false, length = 100)
    private String financerAddress;
    @Column(name="tenure", nullable=false)
    private Integer tenure;
    @Column(name="emi_amount", nullable=false)
    private Integer emi;
    @Column(name="kyc_number", nullable=false, length = 50)
    private String kycNumber;
    @Column(name="loan_status", nullable=false, length = 20)
    private String loanStatus;
    @Column(name="annual_salary", nullable=false)
    private Long annualSalary;
    @Column(name="form_16_received", nullable=false)
    private Boolean form16Received;
    @Column(name="bank_stmt_received", nullable=false)
    private Boolean bankStatementReceived;
    @Column(name = "approved_by_front_officer", length = 100)
    private String approvedByFrontOfficer;
    @Column(name = "approved_by_risk_officer", length = 100)
    private String approvedByRiskOfficer;
    @Column(name = "approved_by_Loan_officer", length = 100)
    private String approvedByLoanOfficer;

    @Column(name = "approved_On_by_front_officer")
    private Timestamp approvedOnFrontOfficer;
    @Column(name = "approved_On_by_risk_officer")
    private Timestamp approvedOnRiskOfficer;
    @Column(name = "approved_On_by_loan_officer")
    private Timestamp approvedOnLoanOfficer;
}
