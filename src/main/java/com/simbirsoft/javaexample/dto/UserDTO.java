package com.simbirsoft.javaexample.dto;


import java.util.List;

// TODO: Хорошо бы сущность дополнить
// TODO: На вскидку можно добавить список преподавателей, которые его ведут и id предмета
public class UserDTO {
    private String name;
    private Integer age;
    private List<>

    public UserDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserDTO() {

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
