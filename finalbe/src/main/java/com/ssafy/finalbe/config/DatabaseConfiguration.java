package com.ssafy.finalbe.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = "com.ssafy.finalbe.model.mapper"
)
public class DatabaseConfiguration {}