package company;

import company.Entity.Person;
import company.Entity.Vault;

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
