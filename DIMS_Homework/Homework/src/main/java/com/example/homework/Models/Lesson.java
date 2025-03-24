package com.example.homework.Models;

public class Lesson {
    private int id;
    private String Name;
    private int Credits;

    public Lesson() {
    }

    public Lesson(int id, String Name, int Credits) {
        this.id = id;
        this.Name = Name;
        this.Credits = Credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        this.Credits = credits;
    }
}
