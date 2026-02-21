package model;

import common.Identifiable;

import java.io.Serializable;

public class User implements Identifiable, Serializable {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getId() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nEmail: %s\n", this.name, this.email);
    }
}
