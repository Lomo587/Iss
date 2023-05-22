package com.example.issproj.Domain;

public class Project {
    private int id;
    private String nume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Project(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }
}
