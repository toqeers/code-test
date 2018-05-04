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
        "observation",
        "barrier",
        "evaluation",
        "redemption"
})
public class Period {

    @JsonProperty("observation")
    private List<String> observation = new ArrayList<String>();
    @JsonProperty("barrier")
    private Barrier barrier;
    @JsonProperty("evaluation")
    private Evaluation evaluation;
    @JsonProperty("redemption")
    private Redemption redemption;
}