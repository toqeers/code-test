package uk.co.ts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "etickers",
        "riskfreeRateEquities",
        "underlyingCurrency"
})
public class Equities {

    @JsonProperty("etickers")
    private String etickers;
    @JsonProperty("riskfreeRateEquities")
    private List<String> riskfreeRateEquities = new ArrayList<String>();
    @JsonProperty("underlyingCurrency")
    private List<Object> underlyingCurrency = new ArrayList<Object>();
}