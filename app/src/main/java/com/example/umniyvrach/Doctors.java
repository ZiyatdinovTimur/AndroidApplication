package com.example.umniyvrach;

public class Doctors {
    private String id;
    private String name;
    private String surname;
    private String spec;
    private String info;
    private String email;

    public Doctors() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Doctors(String id, String name, String surname, String spec, String info, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.spec = spec;
        this.info=info;
        this.email = email;
    }
}
