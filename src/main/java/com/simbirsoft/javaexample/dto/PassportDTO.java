package com.simbirsoft.javaexample.dto;

public class PassportDTO {

    private String series;
    private String number;

    public PassportDTO(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public PassportDTO() {
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
