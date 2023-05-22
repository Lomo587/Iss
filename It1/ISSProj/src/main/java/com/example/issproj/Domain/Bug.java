package com.example.issproj.Domain;

public class Bug {
    private int id;

    public Bug(int id, int proiect, String descriere, String status) {
        this.id = id;
        this.proiect = proiect;
        this.descriere = descriere;
        this.status = status;
    }

    public int getProiect() {
        return proiect;
    }

    public void setProiect(int proiect) {
        this.proiect = proiect;
    }

    private int proiect;
    private String descriere,status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bug(int id, String descriere, String status) {
        this.id = id;
        this.descriere = descriere;
        this.status = status;
    }
}
