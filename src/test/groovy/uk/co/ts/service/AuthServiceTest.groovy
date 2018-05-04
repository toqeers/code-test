package uk.co.ts.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import uk.co.ts.dto.AuthResponse

import static org.springframework.test.util.ReflectionTestUtils.setField

class AuthServiceTest extends Specification {

    private AuthService service

    def mockRestTemplate = Mock(RestTemplate)

    def grantType = "test-grant-type"
    def clientId = "dummy-client"
    def clientSecret = "very-secret"
    def authServiceUrl = "http://auth.service.url/some/path"

    void setup() {
        service = new AuthService(mockRestTemplate)
        setField(service, "grantType", grantType)
        setField(service, "clientId", clientId)
        setField(service, "clientSecret", clientSecret)
        setField(service, "authServiceUrl", authServiceUrl)
    }

    def "GetAccessToken: should return access token fro auth service"() {
        given: "We get successfull response from auth service"
        def expectedAccessToken = "dummy-access-token"
        def authResp = new AuthResponse(accessToken: expectedAccessToken)
        def respEntity = new ResponseEntity(authResp, HttpStatus.OK)

        when: "When we call getAccessToken"
        def actualToken = service.getAccessToken()

        then: "We should get expected access token back"
        1 * mockRestTemplate.postForEntity(*_) >> respEntity
        actualToken == expectedAccessToken
    }
}
