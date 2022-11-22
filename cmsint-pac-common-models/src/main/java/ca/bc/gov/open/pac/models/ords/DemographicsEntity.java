package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemographicsEntity extends BaseEntity {

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("eventTypeCode")
    private String eventTypeCode;

    @JsonProperty("csNum")
    private String csNum;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("givenName1")
    private String givenName1;

    @JsonProperty("givenName2")
    private String givenName2;

    @JsonProperty("birthDate")
    private String birthDate;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("photoGUID")
    private String photoGUID;

    @JsonProperty("probableDischargeDate")
    private String probableDischargeDate;

    @JsonProperty("outReason")
    private String outReason;

    @JsonProperty("isActive")
    private String isActive;

    @JsonProperty("fromCsNum")
    private String fromCsNum;

    @JsonProperty("mergeUserId")
    private String mergeUserId;

    @JsonProperty("livingUnit")
    private String livingUnit;

    @JsonProperty("icsLocationCd")
    private String icsLocationCd;

    @JsonProperty("isIn")
    private String isIn;

    @JsonProperty("sysDate")
    private String sysDate;

    @JsonProperty("avLocaCd")
    private String avLocaCd;

    @JsonProperty("avClientLocaUserId")
    private String avClientLocaUserId;

    @JsonProperty("vLocaCdAlternate")
    private String vLocaCdAlternate;
}
