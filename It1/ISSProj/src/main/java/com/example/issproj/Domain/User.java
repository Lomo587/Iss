package com.example.issproj.Domain;

public class User {
    private int id;
    private String nume,parola,tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public User(int id, String nume, String parola, String tip) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
        this.tip=tip;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
