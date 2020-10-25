
package com.sg.loan.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerDetails implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("kyc_number")
    private String kyc_number;
    @JsonProperty("pan_number")
    private String panNumber;
    @JsonProperty("personal_details")
    private PersonalDetails personalDetails;
    @JsonProperty("identity_details")
    private IdentityDetails identityDetails;
    @JsonProperty("address_details")
    private AddressDetails addressDetails;



}
