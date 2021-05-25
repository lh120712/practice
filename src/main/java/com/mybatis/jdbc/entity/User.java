package com.mybatis.jdbc.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class User {
    private String name;

    private Integer age;

    private Integer id;
}
