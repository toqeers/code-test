package uk.co.ts.controller

import org.springframework.core.io.ClassPathResource

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static org.springframework.http.HttpStatus.OK

class NoAuthFunctionalTest extends AbstractFunctionalTest {

    String riskCaclRequest
    String authResponse
    String riskMeasuresResponse

    def setup() {
        riskCaclRequest = new ClassPathResource("/testData/risk-clalc-request.json").getFile().text
        authResponse = new ClassPathResource("/testData/auth-response.json").getFile().text
        riskMeasuresResponse = new ClassPathResource("/testData/risk-measures-response.json").getFile().text
    }

    def "No Auth risk endpoint should return risk measures"() {
        given:
        givenThat(post(urlPathEqualTo("/oauth/token"))
                .willReturn(aResponse()
                .withStatus(OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody(authResponse))
        )
        givenThat(post(urlPathEqualTo("/v1.1/risk-measures"))
                .willReturn(aResponse()
                .withStatus(OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody(riskMeasuresResponse))
        )

        when:
        def response = restClient.post(path: "/api/v1.0/risk",
                headers: ["Content-type": "application/json", "Accept": "application/json"],
                requestContentType: "application/json",
                body: riskCaclRequest)

        then:
        response.status == OK.value()
        def responseJson = slurper.parse(response.data)
        //just for example validating one proeprty from response
        responseJson.MRM.MRMclass == "3"
    }
}
