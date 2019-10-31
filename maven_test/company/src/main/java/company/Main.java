package main.java.company;

import java.util.UUID;

import main.java.company.Entity.BankAccount;
import main.java.company.Entity.Person;
import main.java.company.Entity.Abstract.ASaveable;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("HII");
        Person p = new Person();
        p.setEmail("jkaskjas").setFirstName("jsjs").setLastName("jkas");
        UUID pId = p.save();
        Person t = (Person) ASaveable.load("Person", pId);
        System.out.println(t.getEmail());
    }
}
