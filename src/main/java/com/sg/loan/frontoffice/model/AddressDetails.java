
package com.sg.loan.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressDetails implements Serializable {
    @JsonProperty("proof_of_address")
    private String proofOfAddress;
    @JsonProperty("address_proof_id")
    private String addressProofId;
    @JsonProperty("address_type")
    private String addressType;
    @JsonProperty("address")
    private Address address;

}
