package Entity.Abstract;

import Entity.Interface.IAccount;
import Entity.Interface.ICustomer;
import Entity.Interface.IEmployee;
import Entity.Interface.IVault;

import java.util.HashMap;

public abstract class AVault implements IVault {
    protected HashMap<Long, ICustomer> customers;
    protected HashMap<Long, IEmployee> employees;
    protected HashMap<Long, IAccount> accounts;

    public HashMap<Long, ICustomer> getCustomers()
    {
        return customers;
    }

    public AVault setCustomers(HashMap<Long, ICustomer> customers)
    {
        this.customers = customers;
        return this;
    }

    public HashMap<Long, IEmployee> getEmployees()
    {
        return employees;
    }

    public AVault setEmployees(HashMap<Long, IEmployee> employees)
    {
        this.employees = employees;
        return this;
    }

    public HashMap<Long, IAccount> getAccounts()
    {
        return accounts;
    }

    public AVault setAccounts(HashMap<Long, IAccount> accounts)
    {
        this.accounts = accounts;
        return this;
    }
}
