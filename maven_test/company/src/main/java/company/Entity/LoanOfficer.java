package main.java.company.Entity;

import main.java.company.Entity.Abstract.ALoanOfficer;

public class LoanOfficer extends ALoanOfficer {
    @Override
    public void CreateAccount(CreditAccount account, Customer customer) {
        super.CreateAccount(account, customer);
    }

    @Override
    public void CloseAccount(CreditAccount account) {

    }
}
