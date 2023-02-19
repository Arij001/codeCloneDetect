package com.jianghao.codeclonedetect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.jianghao.codeclonedetect.mapper")
@ComponentScan("com.jianghao.codeclonedetect")
public class CodeCloneDetectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeCloneDetectApplication.class, args);
    }

}
