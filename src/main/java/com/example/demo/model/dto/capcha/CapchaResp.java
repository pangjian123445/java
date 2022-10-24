package com.example.demo.model.dto.capcha;

import lombok.Data;

@Data
public class CapchaResp {
    private int err_no;
    private String err_str;
    private String pic_id;
    private String pic_str;
    private String md5;
}
