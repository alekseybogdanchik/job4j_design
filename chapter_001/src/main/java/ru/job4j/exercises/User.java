package ru.job4j.exercises;

import java.util.Objects;
import java.util.Set;


public class User {
    private String name;
    private Set<String> emailList;

    public User(String name, Set<String> emailList) {
        this.name = name;
        this.emailList = emailList;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmailList() {
        return emailList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
