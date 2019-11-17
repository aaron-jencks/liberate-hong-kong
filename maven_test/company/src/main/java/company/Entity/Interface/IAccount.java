package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.BankAccount;
import company.Entity.CreditAccount;

public interface IAccount extends ISaveable {
    public long getAmount();

    public UUID setAmount(long amount);

    public static IAccount load(JSONObject obj) {
        UUID objId = (UUID) obj.get(ISaveable.ID_STR_CONST);
        String type = obj.getString(ISaveable.TYPE_STR_CONST);
        IAccount e = null;
        switch (type) {
        case "BankAccount":
            e = IBankAccount.load(objId);
            break;
        case "CreditAccount":
            e = ICreditAccount.load(objId);
            break;
        default:
            System.out.println("Unable to load IAccount with type = " + type);
            break;
        }
        return e;
    }

    public static JSONArray loadAllAccounts() {
        return ISaveable.add(ISaveable.loadAllAsJson(BankAccount.class.getSimpleName()),
                ISaveable.loadAllAsJson(CreditAccount.class.getSimpleName()));
    }
}
