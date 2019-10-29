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
}
