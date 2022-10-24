package com.example.demo.model.dto.capcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCaptchaDto {
    private String captcha;
    private Long expired;
}
