package uk.co.ts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "name",
        "issueDate",
        "maturityDate",
        "horizon",
        "riskfreeRateSettlement",
        "equities",
        "numerics",
        "reversed",
        "terminalPayoff",
        "reference",
        "earlyRedemptionFeature",
        "entryCost",
        "IHP1midbid",
        "IHP2midbid",
        "exitCost",
        "ongoingTransaction",
        "ongoingInsurance",
        "ongoingOther",
        "performanceFee",
        "carriedInterest"
})
public class RiskCalculationRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("issueDate")
    private String issueDate;
    @JsonProperty("maturityDate")
    private String maturityDate;
    @JsonProperty("horizon")
    private Double horizon;
    @JsonProperty("riskfreeRateSettlement")
    private String riskfreeRateSettlement;
    @JsonProperty("equities")
    private Equities equities;
    @JsonProperty("numerics")
    private String numerics;
    @JsonProperty("reversed")
    private Boolean reversed;
    @JsonProperty("terminalPayoff")
    private TerminalPayoff terminalPayoff;
    @JsonProperty("reference")
    private Reference reference;
    @JsonProperty("earlyRedemptionFeature")
    private EarlyRedemptionFeature earlyRedemptionFeature;
    @JsonProperty("entryCost")
    private Integer entryCost;
    @JsonProperty("IHP1midbid")
    private Double iHP1midbid;
    @JsonProperty("IHP2midbid")
    private Double iHP2midbid;
    @JsonProperty("exitCost")
    private Double exitCost;
    @JsonProperty("ongoingTransaction")
    private Integer ongoingTransaction;
    @JsonProperty("ongoingInsurance")
    private Integer ongoingInsurance;
    @JsonProperty("ongoingOther")
    private Double ongoingOther;
    @JsonProperty("performanceFee")
    private Integer performanceFee;
    @JsonProperty("carriedInterest")
    private Integer carriedInterest;
}