package br.com.kbmg28.wsrecaptchaenterpriserest.service;

import br.com.kbmg28.wsrecaptchaenterpriserest.model.LoginDto;
import br.com.kbmg28.wsrecaptchaenterpriserest.model.RecaptchaEnterpriseRequest;
import br.com.kbmg28.wsrecaptchaenterpriserest.model.RecaptchaEnterpriseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class LoginService {

    @Value("${recaptcha.enterprise.siteKey}")
    private String siteKey;

    @Value("${recaptcha.enterprise.projectID}")
    private String projectID;

    @Value("${recaptcha.enterprise.apiKey}")
    private String apiKey;

    public String login(String recaptchaResponse, LoginDto loginDto) {
        RestTemplate restTemplate = new RestTemplate();
        String recaptchaUrlTemplate = "https://recaptchaenterprise.googleapis.com/v1/projects/%s/assessments?key=%s";

        String urlRecaptchaEnterpriseFormatted = String.format(recaptchaUrlTemplate, projectID, apiKey);
        RecaptchaEnterpriseRequest recaptchaEnterpriseRequest = new RecaptchaEnterpriseRequest(recaptchaResponse, siteKey, "");
        ResponseEntity<RecaptchaEnterpriseResponse> response = restTemplate.postForEntity(urlRecaptchaEnterpriseFormatted, recaptchaEnterpriseRequest, RecaptchaEnterpriseResponse.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            validateRecaptchaEnterpriseResponse(response);
            return generateJwt();
        }
        log.error("Failed to validate recaptcha enterprise, response: [{}] {}", response.getStatusCode(), response);
        throw new RuntimeException("Failed to validate recaptcha");
    }

    private String generateJwt() {
        return "myToken";
    }

    private void validateRecaptchaEnterpriseResponse(ResponseEntity<RecaptchaEnterpriseResponse> response) {
        RecaptchaEnterpriseResponse recaptchaEnterpriseResponse = response.getBody();

        if (recaptchaEnterpriseResponse == null) {
            String messageError = "Failed to get response recaptcha enterprise";
            log.error(messageError);

            throw new RuntimeException(messageError);
        }

        log.info("Recaptcha enterprise response: [{}] {}", response.getStatusCode(), recaptchaEnterpriseResponse);

        boolean isTokenValid = recaptchaEnterpriseResponse.getTokenProperties().isValid();
        double scoreResult = recaptchaEnterpriseResponse.getRiskAnalysis().getScore();

        if (isTokenValid && scoreResult > 0.5) {
            log.info("Success validated recaptcha enterprise");
            return;
        }

        String messageError = String.format("Invalid recaptcha. Validation result: %s, Score: %.2f", isTokenValid, scoreResult);
        log.error(messageError);
        throw new RuntimeException(messageError);
    }
}
