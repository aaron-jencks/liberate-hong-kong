package Entity.Abstract;

import Entity.Account;
import Entity.Customer;
import Entity.Employee;
import Entity.Interface.IVault;

import java.util.HashMap;

public abstract class AVault implements IVault {
    protected HashMap<Long, Customer> customers;
    protected HashMap<Long, Employee> employees;
    protected HashMap<Long, Account> accounts;
}
