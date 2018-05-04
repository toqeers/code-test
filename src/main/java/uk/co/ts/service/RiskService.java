package uk.co.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.ts.dto.RiskCalculationRequest;

import java.util.Collections;

@Service
public class RiskService {

    @Value("${ext-risk-api-url}")
    private String externalRiskApi;

    private final AuthService authService;
    private final RestTemplate restTemplate;

    @Autowired
    public RiskService(AuthService authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @Cacheable("riskMeasuresCache")
    public String getRiskMeasures(RiskCalculationRequest riskCalcReq) {
        String accessToken = authService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity<RiskCalculationRequest> reqEntity = new HttpEntity<>(riskCalcReq, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(externalRiskApi, reqEntity, String.class);

        return response.getBody();
    }
}
