package com.xolisa.creditcardrestapi.entity;

import org.springframework.data.annotation.Id;

public class Country {

    @Id
    private String iso2;
    private String name;
    private boolean banned;
    private String flag;

    public Country(String iso2, String name, boolean banned, String flag) {
        this.iso2 = iso2;
        this.name = name;
        this.banned = banned;
        this.flag = flag;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
