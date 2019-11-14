package company.Entity.Interface;

import company.Entity.CreditAccount;
import company.Entity.Customer;

public interface ILoanOfficer extends IEmployee{
    /**
     * Creates a new Credit Account and assigns it to the given customer
     * @param account
     * @param customer
     */
    public void CreateAccount(CreditAccount account, Customer customer);

    /**
     * Closes a credit account
     * @param account
     */
    public void CloseAccount(CreditAccount account);
}
