package com.example.umniyvrach;

public class User {
    private String id;
    private String name;
    private String surname;
    private String age;
    private String email;
    private String info;
    private String male;
    private String card;
    public String morningRemainds;
    private String dayRemainds;
    private String eveningRemainds;

    public User(String id, String name, String surname, String age, String email,
                String info, String male, String card, String morningRemainds, String dayRemainds, String eveningRemainds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.info = info;
        this.male = male;
        this.card = card;
        this.morningRemainds = morningRemainds;
        this.dayRemainds = dayRemainds;
        this.eveningRemainds = eveningRemainds;
    }

    public String getMorningRemainds() {
        return morningRemainds;
    }

    public void setMorningRemainds(String morningRemainds) {
        this.morningRemainds = morningRemainds;
    }

    public String getDayRemainds() {
        return dayRemainds;
    }

    public void setDayRemainds(String dayRemainds) {
        this.dayRemainds = dayRemainds;
    }

    public String getEveningRemainds() {
        return eveningRemainds;
    }

    public void setEveningRemainds(String eveningRemainds) {
        this.eveningRemainds = eveningRemainds;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public User() {

    }
}
