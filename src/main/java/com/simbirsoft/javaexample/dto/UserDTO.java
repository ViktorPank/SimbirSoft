package com.simbirsoft.javaexample.dto;


// TODO: Хорошо бы сущность дополнить
// TODO: На вскидку можно добавить список преподавателей, которые его ведут и id предмета
public class UserDTO {
    private String name;
    private Integer course;

    public UserDTO(String name, Integer course) {
        this.name = name;
        this.course = course;
    }

    public UserDTO() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}
