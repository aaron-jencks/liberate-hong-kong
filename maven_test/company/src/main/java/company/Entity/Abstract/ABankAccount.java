package company.Entity.Abstract;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.IBankAccount;
import company.Entity.Interface.ISaveable;

public abstract class ABankAccount extends AAccount implements IBankAccount {

    public static BankAccount load(UUID id){
        Object o = ISaveable.load(BankAccount.class, id);
        return (BankAccount)o;
    }

    public static JSONArray loadAllBankAccounts(){
        JSONArray banks = ISaveable.loadAllAsJson("BankAccount");
        JSONArray credit = ISaveable.loadAllAsJson("CreditAccount");
        return ISaveable.add(banks, credit);
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
}
