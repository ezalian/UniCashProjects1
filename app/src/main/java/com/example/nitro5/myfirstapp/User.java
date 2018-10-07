package com.example.nitro5.myfirstapp;

public class User {
    public int pin;
    public String username;
   public String email;
    public String phone;
   public String password;


    public User(){

    }

    public User(String username, String phone, String password,String email) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;


    }

public String getEmail(){
        return email;

}

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPin(){
        return pin;

    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
