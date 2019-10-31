package company;

import java.util.UUID;

import org.json.JSONArray;

import company.Entity.BankAccount;
import company.Entity.Person;
import company.Entity.Teller;
import company.Entity.Abstract.ASaveable;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("HII");
        Teller t = new Teller();
        t.setEmployeeUsername("u");
        t.setEmployeePassword("p");
        t.save();
        JSONArray a = ASaveable.loadAllAsJson("Employee");
        for(int i = 0; i < a.length(); i++){
            System.out.println(a.get(i).toString());
        }
    }
}
