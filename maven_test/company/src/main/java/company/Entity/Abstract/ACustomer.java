package main.java.company.Entity.Abstract;

import main.java.company.Entity.Interface.ICustomer;

import java.util.ArrayList;

public abstract class ACustomer extends APerson implements ICustomer
{
    protected ArrayList<String> accountIds;

    public ArrayList<String> getAccountIds()
    {
        return accountIds;
    }

    public ACustomer setAccountIds(ArrayList<String> accountIds)
    {
        this.accountIds = accountIds;
        return this;
    }
}
