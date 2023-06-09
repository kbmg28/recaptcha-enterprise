package br.com.kbmg28.wsrecaptchaenterpriserest.model;

import lombok.Data;

@Data
public class LoginDto {
    private String user;
    private String password;
}
