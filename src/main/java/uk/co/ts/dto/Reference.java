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
        "initialLookback",
        "initialLookbackSummary",
        "finalLookback",
        "finalLookbackSummary"
})
public class Reference {

    @JsonProperty("initialLookback")
    private List<String> initialLookback = new ArrayList<String>();
    @JsonProperty("initialLookbackSummary")
    private String initialLookbackSummary;
    @JsonProperty("finalLookback")
    private List<String> finalLookback = new ArrayList<String>();
    @JsonProperty("finalLookbackSummary")
    private String finalLookbackSummary;
}