package br.com.kbmg28.wsrecaptchaenterpriserest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDto {
    private String jwt;
}
