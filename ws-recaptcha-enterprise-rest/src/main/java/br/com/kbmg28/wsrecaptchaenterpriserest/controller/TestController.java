package br.com.kbmg28.wsrecaptchaenterpriserest.controller;

import br.com.kbmg28.wsrecaptchaenterpriserest.model.JwtTokenDto;
import br.com.kbmg28.wsrecaptchaenterpriserest.model.LoginDto;
import br.com.kbmg28.wsrecaptchaenterpriserest.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/recaptcha-rest")
@RequiredArgsConstructor
public class TestController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(
            @RequestBody @Valid LoginDto loginDto, @RequestParam(name="g-recaptcha-response") String recaptchaResponse) {
        String jwt = loginService.login(recaptchaResponse, loginDto);

        return ResponseEntity.ok(new JwtTokenDto(jwt));
    }
}
