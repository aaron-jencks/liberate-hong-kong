package com.company.entity;

abstract class APerson{
    
    public String firstName;
    public String lastName;

    public String GetFullName(){
        return firstName + " " + lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

}