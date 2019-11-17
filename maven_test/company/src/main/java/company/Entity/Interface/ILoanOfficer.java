package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.CreditAccount;
import company.Entity.Customer;
import company.Entity.LoanOfficer;

public interface ILoanOfficer extends IEmployee{
    public void CreateAccount(CreditAccount account, Customer customer);
    public void CloseAccount(CreditAccount account);

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static LoanOfficer load(UUID id){
        Object o = ISaveable.load(LoanOfficer.class, id);
        return (LoanOfficer)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static ILoanOfficer load(JSONObject obj){
        Object o = ISaveable.load(LoanOfficer.class, obj);
        return (ILoanOfficer)o;
    }
}
