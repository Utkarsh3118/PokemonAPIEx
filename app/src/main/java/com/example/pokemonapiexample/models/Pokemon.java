package com.example.pokemonapiexample.models;

import android.provider.Telephony;

public class Pokemon
{
    private String name;
    private String url;
    private int number;

    public int getNumber() {

        String[] urlParse = url.split("/");
        return Integer.parseInt(urlParse[urlParse.length-1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
