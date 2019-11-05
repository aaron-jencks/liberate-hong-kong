package company.Entity.Abstract;

import java.lang.reflect.Field;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.IBankAccount;

public abstract class ABankAccount extends AAccount implements IBankAccount {

    public static BankAccount load(UUID id){
        Object o = ASaveable.load(BankAccount.class, id);
        return (BankAccount)o;
    }

    public static JSONArray loadAllBankAccounts(){
        JSONArray banks = loadAllAsJson("BankAccount");
        JSONArray credit = loadAllAsJson("CreditAccount");
        return add(banks, credit);
    }

    public static BankAccount findById(String accountId) {
        JSONArray arr = loadAllBankAccounts();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            if (obj.getString("accountNumber").equals(accountId)) {
                UUID id = UUID.fromString(obj.getString("ID"));
                return load(id);
            }
        }
        return null;
    }

    public long closeAccount(){
        long amt = this.getAmount();
        this.setAmount(0);
        this.save();
        return amt;
    }

    public static String createAccount(){
        BankAccount ba = new BankAccount();
        ba.setAmount(0l);
        UUID id = UUID.randomUUID();
        ba.setAccountNumber(id.toString());
        ba.save();
        return id.toString();
    }
}
