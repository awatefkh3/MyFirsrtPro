package com.example.myfirsrtpro;

public class User {
    private String email;
    private String password;
    private String name;
    private int age;
    private boolean female;
    private String key;
    private String image;


    public User(String email, String password, String name, int age, boolean female) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.female = female;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User findUser(String key){
        return this;
    }

}
