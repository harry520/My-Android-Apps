package com.example.harryjiang.searchview;

public class Country {

    private int flagId;
    private String name;

    public Country(int flagId, String name) {
        this.setFlagId(flagId);
        this.setName(name);
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
