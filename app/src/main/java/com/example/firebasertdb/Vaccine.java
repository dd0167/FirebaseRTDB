package com.example.firebasertdb;

public class Vaccine {

    private String place;
    private String date;

    public Vaccine (String place, String date) {
        this.place=place;
        this.date=date;
    }

    public Vaccine()
    {
    }

    public String getPlace()
    {
        return place;
    }

    public String getDate()
    {
        return date;
    }
}
