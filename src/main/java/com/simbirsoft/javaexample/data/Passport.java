package com.simbirsoft.javaexample.data;

import javax.persistence.*;

@Table
@Entity(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passport_id;

    @Column
    private String series;

    @Column
    private String number;

    @OneToOne(optional=false, mappedBy="passport")
    private Person owner;

    public Passport() {}

    public Passport(String series, String number ) {
        this.series = series;
        this.number = number;
    }

    public Long getPassport_id() {
        return passport_id;
    }

    public void setPassport_id(Long passport_id) {
        this.passport_id = passport_id;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
