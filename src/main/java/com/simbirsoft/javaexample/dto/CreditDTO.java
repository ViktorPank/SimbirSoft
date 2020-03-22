package com.simbirsoft.javaexample.dto;

public class CreditDTO {

    private Long amount;
    private String currency;

    public CreditDTO(Long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public CreditDTO() {
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
}
