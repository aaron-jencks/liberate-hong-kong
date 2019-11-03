package company;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.Person;
import company.Entity.Teller;
import company.Entity.Abstract.ASaveable;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BankAccount ba = new BankAccount();
        ba.setAccountNumber("test");
        ba.setAmount(123l);
        UUID id = ba.save();

        BankAccount ac = BankAccount.load(id);
        System.out.println(ac.getAccountNumber() + " " + ac.getAmount());
    }
}
