package company.Entity.Abstract;

import company.Entity.*;
import company.Entity.Interface.IAccount;
import company.Entity.Interface.ICustomer;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.IVault;
import company.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class AVault extends ASaveable implements IVault
{
    protected HashMap<UUID, ICustomer> customers = new HashMap<UUID, ICustomer>();
    protected HashMap<UUID, IEmployee> employees = new HashMap<UUID, IEmployee>();
    protected HashMap<UUID, IAccount> accounts = new HashMap<UUID, IAccount>();
    public ArrayList<UUID> customerIds = new ArrayList<>();
    public ArrayList<UUID> employeeIds = new ArrayList<>();
    public ArrayList<UUID> accountIds = new ArrayList<>();

    public static Vault load(UUID id){
        Object o = ASaveable.load(Vault.class, id);
        return (Vault)o;
    }

    @Override
    public UUID save() {
        for(ICustomer c: this.customers.values()){
            customerIds.add(c.save());
        }
        for(IEmployee e: this.employees.values()){
            employeeIds.add(e.save());
        }
        for(IAccount a: this.accounts.values()){
            accountIds.add(a.save());
        }
        System.out.println(employeeIds.toArray().toString());
        return UUID.randomUUID();
    }

    public HashMap<UUID, ICustomer> getCustomers()
    {
        return customers;
    }

    public AVault setCustomers(HashMap<UUID, ICustomer> customers)
    {
        this.customers = customers;
        return this;
    }

    public HashMap<UUID, IEmployee> getEmployees()
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

    public IEmployee getEmployee(UUID employee_id)
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

    public ICustomer getCustomer(UUID customerId){
        return customers.get(customerId);
    }

    public ICustomer getCustomer(Person p){
        if (employees != null) {
            for (ICustomer customer : customers.values()) {
                if ((APerson) customer == p)
                    return customer;
            }
        }
        return null;
    }

    public AVault setEmployees(HashMap<UUID, IEmployee> employees)
    {
        this.employees = employees;
        return this;
    }

    public HashMap<UUID, IAccount> getAccounts()
    {
        return accounts;
    }

    public AVault setAccounts(HashMap<UUID, IAccount> accounts)
    {
        this.accounts = accounts;
        return this;
    }

    public UUID createTeller(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Teller t = new Teller(p.getFirstName(), p.getLastName(), username);
        this.employees.put(t.objId, t);
        return t.objId;
    }

    public UUID createLoanOfficer(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        LoanOfficer lo = new LoanOfficer(p.getFirstName(), p.getLastName(), username);
        this.employees.put(lo.objId, lo);
        return lo.objId;
    }

    public UUID createManager(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Manager m = new Manager(p.getFirstName(), p.getLastName(), username);
        this.employees.put(m.objId, m);
        return m.objId;
    }

    public UUID createHRManager(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        HRManager hrm = new HRManager(p.getFirstName(), p.getLastName(), username);
        this.employees.put(hrm.objId, hrm);
        return hrm.objId;
    }

    public UUID createOwner(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Owner o = new Owner(p.getFirstName(), p.getLastName(), username);
        this.employees.put(o.objId, o);
        return o.objId;
    }

    public UUID fireEmployee(UUID employee_id)
    {
        employees.remove(employee_id);
        return employee_id;
    }
}
