package br.com.kbmg28.wsrecaptchaenterpriserest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecaptchaEnterpriseRequest {
    private Event event;

    public RecaptchaEnterpriseRequest(String token, String siteKey, String expectedAction) {
        this.event = new Event(token, siteKey, expectedAction);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Event {
        private String token;
        private String siteKey;
        private String expectedAction;
    }
}
