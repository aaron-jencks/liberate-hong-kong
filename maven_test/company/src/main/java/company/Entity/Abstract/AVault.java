package company.Entity.Abstract;

import company.Entity.*;
import company.Entity.Interface.IAccount;
import company.Entity.Interface.ICustomer;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.IVault;
import company.exceptions.EmployeeNotFoundException;

import java.util.HashMap;

public abstract class AVault implements IVault
{
    private long lastEmployeeID = 0;
    protected HashMap<Long, ICustomer> customers = new HashMap<Long, ICustomer>();
    protected HashMap<Long, IEmployee> employees = new HashMap<Long, IEmployee>();
    protected HashMap<Long, IAccount> accounts = new HashMap<Long, IAccount>();

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

    public IEmployee getEmployee(Person p) {
        if (employees != null) {
            for (IEmployee employee : employees.values()) {
                if ((APerson) employee == p)
                    return employee;
            }
        }
        return null;
    }

    public IEmployee getEmployee(long employee_id)
    {
        return employees.get(employee_id);
    }

    public IEmployee getEmployee(String username)
    {
        for(IEmployee e : employees.values())
            if(e.getEmployeeUsername().equals(username))
                return e;
        return null;
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

    public long createTeller(Person p)
    {
        long new_id = ++lastEmployeeID;
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Teller t = new Teller(p.getFirstName(), p.getLastName(), username, new_id);
        this.employees.put(new_id, t);
        return new_id;
    }

    public long createLoanOfficer(Person p)
    {
        long new_id = ++lastEmployeeID;
        String username = p.getFirstName().charAt(0) + p.getLastName();
        LoanOfficer lo = new LoanOfficer(p.getFirstName(), p.getLastName(), username, new_id);
        this.employees.put(new_id, lo);
        return new_id;
    }

    public long createManager(Person p)
    {
        long new_id = ++lastEmployeeID;
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Manager m = new Manager(p.getFirstName(), p.getLastName(), username, new_id);
        this.employees.put(new_id, m);
        return new_id;
    }

    public long createHRManager(Person p)
    {
        long new_id = ++lastEmployeeID;
        String username = p.getFirstName().charAt(0) + p.getLastName();
        HRManager hrm = new HRManager(p.getFirstName(), p.getLastName(), username, new_id);
        this.employees.put(new_id, hrm);
        return new_id;
    }

    public long createOwner(Person p)
    {
        long new_id = ++lastEmployeeID;
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Owner o = new Owner(p.getFirstName(), p.getLastName(), username, new_id);
        this.employees.put(new_id, o);
        return new_id;
    }

    public long fireEmployee(long employee_id)
    {
        employees.remove(employee_id);
        return employee_id;
    }
}
