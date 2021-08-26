package com.example.test2;

public class students {

    String id;
    String name;
    String age;

    public students(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public students(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public students() {
    }


}
