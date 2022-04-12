package com.example.swift;

public class Exercise {
    private String name;
    private String date;
    private String info;

    public Exercise(String name, String date, String info){
        this.name = name;
        this.date = date;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public String getName(){
        return name;
    }

    public String getInformation() {return info;}
}
