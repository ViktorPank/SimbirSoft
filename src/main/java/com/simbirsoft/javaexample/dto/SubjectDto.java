package com.simbirsoft.javaexample.dto;


// TODO: Хорошо бы сущность дополнить
// TODO: На вскидку можно добавить список преподавателей, которые его ведут и id предмета
public class SubjectDto {
    private String title;
    private Integer course;

    public SubjectDto(String title, Integer course) {
        this.title = title;
        this.course = course;
    }

    public SubjectDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}
