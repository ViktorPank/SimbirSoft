package com.simbirsoft.javaexample.dto;


import java.util.List;

public class UserDTO {
    private Integer id;
    private String name;
    private Integer age;
    private List<String> family;

    public UserDTO(String name, Integer age, List<String> family, Integer id) {
        this.name = name;
        this.age = age;
        this.family = family;
        this.id = id;
    }

    public UserDTO() {

    }

    public List<String> getFamily() {
        return family;
    }

    public void setFamily(List<String> family) {
        this.family = family;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
