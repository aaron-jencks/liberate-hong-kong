package company.Entity.Abstract;

import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.IBankAccount;

public abstract class ABankAccount extends AAccount implements IBankAccount {

    public static BankAccount findById(String accountId) {
        JSONArray arr = ASaveable.loadAllAsJson("Account");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            if (obj.getString("accountId").equals(accountId)) {
                Object inst = instantiate("BankAccount");
                Class<?> clazz = inst.getClass();
                // fill all the attributes
                Field[] fields = clazz.getSuperclass().getDeclaredFields();
                if (fields.length == 0) {
                    fields = clazz.getSuperclass().getSuperclass().getDeclaredFields();
                }
                for (Field f : fields) {
                    try {
                        f.set(inst, obj.get(f.getName()));
                    }
                    catch (IllegalAccessException | IllegalArgumentException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                return (BankAccount) inst;
            }
        }
        return new BankAccount();
    }

    public long closeAccount(){
        long amt = this.getAmount();
        this.setAmount(0);
        this.save();
        return amt;
    }
}
