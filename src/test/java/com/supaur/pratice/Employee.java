package com.supaur.pratice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 894688524334687405L;

    private Integer eno;

    private String name;

    private Integer age;

    private double salary;

    private Status status;

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
