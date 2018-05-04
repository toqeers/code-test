package uk.co.ts.controller

import org.springframework.http.HttpStatus
import spock.lang.Specification
import uk.co.ts.dto.RiskCalculationRequest
import uk.co.ts.service.RiskService

class NoAuthRiskControllerTest extends Specification {

    private NoAuthRiskController controller

    def mockRiskService = Mock(RiskService)

    void setup() {
        controller = new NoAuthRiskController(mockRiskService)
    }

    def "GetRiskMeasuresJson: should return risk measures"() {
        given: "we get risk measures from service successfully"
        def expectedRiskMeasures = "dummy risk measures"
        def riskCalcRequest = new RiskCalculationRequest()

        when: "we call controller"
        def response = controller.getRiskMeasuresJson(riskCalcRequest)

        then: "we should get expected risk measures"
        1 * mockRiskService.getRiskMeasures(riskCalcRequest) >> expectedRiskMeasures

        response.statusCode == HttpStatus.OK
        response.body == expectedRiskMeasures
    }
}
