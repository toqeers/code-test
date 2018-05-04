package uk.co.ts.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.ts.dto.RiskCalculationRequest;
import uk.co.ts.service.RiskService;

import javax.ws.rs.core.MediaType;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableAutoConfiguration
public class NoAuthRiskController {

    private static final Logger LOGGER = getLogger(NoAuthRiskController.class);

    private final RiskService service;

    @Autowired
    public NoAuthRiskController(RiskService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return Risk mrasures for unauthenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "missing body or invalid json request")
    })
    @RequestMapping(value = "/api/v1.0/risk", method = POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getRiskMeasuresJson(@RequestBody RiskCalculationRequest riskCalculationRequest) {

        String riskMeasures = service.getRiskMeasures(riskCalculationRequest);

        return new ResponseEntity<>(riskMeasures, HttpStatus.OK);
    }
}
