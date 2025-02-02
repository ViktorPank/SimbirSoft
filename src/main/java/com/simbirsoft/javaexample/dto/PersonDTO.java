package com.simbirsoft.javaexample.dto;

public class PersonDTO {

    private String username;

    private String password;

    private Integer age;

    public PersonDTO(String username,String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public PersonDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
