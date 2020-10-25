
package com.sg.loan.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IdentityDetails implements Serializable {

    @JsonProperty("identity_type")
    private String identityType;
    @JsonProperty("identity_number")
    private String identityNumber;


}
