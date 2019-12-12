package com.xolisa.creditcardrestapi.entity;

import org.springframework.data.annotation.Id;

public class CreditCard {

    @Id
    private String number;
    private String countryName;
    private String countryIso2;
    private String countryFlag;

    public CreditCard(String number, String countryName, String countryIso2, String countryFlag) {
        this.number = number;
        this.countryName = countryName;
        this.countryIso2 = countryIso2;
        this.countryFlag = countryFlag;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }
}
