package br.com.kbmg28.wsrecaptchaenterpriserest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecaptchaEnterpriseResponse {
    private String name;

    @NotNull
    private TokenProperties tokenProperties;
    private RiskAnalysis riskAnalysis;
    private Event event;

    @Data
    public static class TokenProperties {
        private boolean valid;
        private String invalidReason;
        private String hostname;
        private String action;
        private String createTime;
    }

    @Data
    public static class RiskAnalysis {
        private double score;
        private List<String> reasons;
    }

    @Data
    public static class Event {
        private String token;
        private String siteKey;
        private String expectedAction;
    }

}
