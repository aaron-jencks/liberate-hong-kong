package company;

import java.lang.reflect.Field;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.Person;
import company.Entity.Teller;
import company.Entity.Vault;
import company.Entity.Abstract.ASaveable;
import company.Entity.Abstract.ATeller;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Vault v = Vault.getInstance();
        Person p = new Person("a","b");
        v.createTeller(p);
        v.createHRManager(p);
        v.save();
    }
}
