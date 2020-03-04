package com.simbirsoft.javaexample.dto;

public class SubjectDto {
    private  String title;
    private  Integer course;

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
