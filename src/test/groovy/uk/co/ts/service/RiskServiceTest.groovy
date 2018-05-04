package uk.co.ts.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import uk.co.ts.dto.RiskCalculationRequest

import static org.springframework.test.util.ReflectionTestUtils.setField

class RiskServiceTest extends Specification {

    private RiskService service

    def mockAuthService = Mock(AuthService)
    def mockRestTemplate = Mock(RestTemplate)
    def externalRiskApi = "http://external.risk.api/url"

    void setup() {
        service = new RiskService(mockAuthService, mockRestTemplate)
        setField(service, "externalRiskApi", externalRiskApi)
    }

    def "GetRiskMeasures: Should return risk calculations"() {
        given: "we get access token and risk calculation response successfully"
        def accessToken = "dummy-access-token"
        def expectedRiskMeasures = "dummy risk measures"
        def respEntity = new ResponseEntity(expectedRiskMeasures, HttpStatus.OK)
        def riskCalcRequest = new RiskCalculationRequest()

        when: "we call getRiskMeasures"
        def actualRiskMeasures = service.getRiskMeasures(riskCalcRequest)

        then: "we should get expected risk measures"
        1 * mockAuthService.getAccessToken() >> accessToken
        1 * mockRestTemplate.postForEntity(*_) >> respEntity

        actualRiskMeasures == expectedRiskMeasures
    }
}
