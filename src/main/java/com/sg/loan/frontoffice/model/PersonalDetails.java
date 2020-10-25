
package com.sg.loan.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PersonalDetails {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("father_name")
    private String fatherName;
    @JsonProperty("mother_name")
    private String motherName;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("gender")
    private String gender;
    private String phone;
    @JsonProperty("marital_status")
    private String maritalStatus;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("occupation")
    private String occupation;
    @JsonProperty("sector")
    private String sector;


}
