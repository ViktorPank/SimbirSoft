package com.simbirsoft.javaexample.data;

import javax.persistence.*;

@Table(name = "credit")
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credit_id;

    @Column
    private Long amount;

    @Column
    private String currency;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Credit(Long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Credit() {
    }

    public Long getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(Long credit_id) {
        this.credit_id = credit_id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
