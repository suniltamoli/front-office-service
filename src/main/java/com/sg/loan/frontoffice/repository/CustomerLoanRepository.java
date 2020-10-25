package com.sg.loan.frontoffice.repository;

import com.sg.loan.frontoffice.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CustomerLoanRepository extends JpaRepository<LoanRequestEntity, Long> {


    @Query("SELECT u FROM LoanRequestEntity u WHERE u.loanRefNumber = ?1")
    List<LoanRequestEntity> getLoanDetailsByloanRefNumber(String loanRefNum);

    @Modifying
    @Query("update  LoanRequestEntity u set u.approvedByLoanOfficer = ?1, u.approvedOnLoanOfficer=?2 , u.loanStatus=?3 where u.loanRefNumber=?4")
    int updateByLoanofficer(String approvedByLoanOfficer, Timestamp approvedOnLoanOfficer, String loanStatus, String loanRefNumber);

    @Modifying
    @Query("update  LoanRequestEntity u set u.approvedByRiskOfficer = ?1, u.approvedOnRiskOfficer=?2 , u.loanStatus=?3 where u.loanRefNumber=?4")
    int updateByRiskofficer(String approvedByLoanOfficer, Timestamp approvedOnLoanOfficer, String loanStatus,String loanRefNumber);


}
