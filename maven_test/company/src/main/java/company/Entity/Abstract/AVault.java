package company.Entity.Abstract;

import company.Entity.*;
import company.Entity.Interface.IAccount;
import company.Entity.Interface.ICustomer;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.ISaveable;
import company.Entity.Interface.IVault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class AVault implements IVault
{

    /**
     * Get all saved customers
     * @return
     */
    public JSONArray getCustomers()
    {
        return ISaveable.loadAllAsJson(ACustomer.class.getSimpleName());
    }

    /**
     * Get all saved employees
     */
    public JSONArray getEmployees()
    {
        return IEmployee.loadAllEmployees();
    }

    /**
     * Get a saved employee that matches the person
     */
    public IEmployee getEmployee(Person p) {
        return getEmployee(p.getObjectId());
    }

    /**
     * Get the saved employee with the id
     */
    public IEmployee getEmployee(UUID employee_id)
    {
        JSONArray e = getEmployees();
        for(int i = 0; i < e.length(); i++){
            JSONObject o = e.getJSONObject(i);
            UUID oId = (UUID) o.get(ISaveable.ID_STR_CONST);
            if (oId.equals(employee_id)) {
                return IEmployee.load(o);
            }
        }
        return null;
    }

    /**
     * Get the saved employee with the username
     */
    public IEmployee getEmployee(String username)
    {
        JSONArray e = getEmployees();
        for(int i = 0; i < e.length(); i++){
            JSONObject o = e.getJSONObject(i);
            String user = o.getString("employeeUsername");
            if (user.equals(username)) {
                return IEmployee.load(o);
            }
        }
        return null;
    }

    /**
     * Get the saved customer with the customer id
     */
    public ICustomer getCustomer(UUID customerId){
        return ICustomer.load(customerId);
    }

    /**
     * Get the saved customer that matches the person
     */
    public ICustomer getCustomer(Person p){
        return getCustomer(p.getObjectId());
    }

    /**
     * Get the saved customer with matching names
     * @param firstName
     * @param lastName
     * @return
     */
    public ICustomer getCustomer(String firstName, String lastName) {
        JSONArray c = getCustomers();
        for(int i = 0; i < c.length(); i++){
            JSONObject o = c.getJSONObject(i);
            String first = o.getString("firstName");
            String last = o.getString("lastName");
            if (last.equals(lastName) && first.equals(firstName)) {
                return ICustomer.load(o);
            }
        }
        return null;
    }

    /**
     * Load all the accounts
     */
    public JSONArray getAccounts()
    {
        return IAccount.loadAllAccounts();
    }

    @Override
    public IAccount getAccount(UUID accountId){
        JSONArray a = getAccounts();
        for(int i = 0; i < a.length(); i++){
            JSONObject o = a.getJSONObject(i);
            UUID id = (UUID) o.get(ISaveable.ID_STR_CONST);
            if (id.equals(accountId)) {
                return IAccount.load(o);
            }
        }
        return null;
    }

    @Override
    public UUID createTeller(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Teller t = new Teller(p, username);
        return t.getObjectId();
    }

    @Override
    public UUID convertToTeller(IEmployee employee, UUID id)
    {
        String username = employee.getFirstName().charAt(0) + employee.getLastName();
        Teller t = new Teller(employee.getFirstName(), employee.getLastName(), username);
        t.setObjectId(id);
        
        return t.getObjectId();
    }

    @Override
    public UUID createLoanOfficer(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        LoanOfficer lo = new LoanOfficer(p, username);
        return lo.getObjectId();
    }

    @Override
    public UUID convertToLoanOfficer(IEmployee employee, UUID id)
    {
        String username = employee.getFirstName().charAt(0) + employee.getLastName();
        LoanOfficer lo = new LoanOfficer(employee.getFirstName(), employee.getLastName(), username);
        return lo.getObjectId();
    }

    @Override
    public UUID createManager(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Manager m = new Manager(p.getFirstName(), p.getLastName(), username);
        m.setObjectId(p.getObjectId());
        return m.getObjectId();
    }

    @Override
    public UUID convertToManager(IEmployee employee, UUID id)
    {
        String username = employee.getFirstName().charAt(0) + employee.getLastName();
        Manager m = new Manager(employee.getFirstName(), employee.getLastName(), username);
        m.setObjectId(id);
        return m.getObjectId();
    }

    @Override
    public UUID createHRManager(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        HRManager hrm = new HRManager(p.getFirstName(), p.getLastName(), username);
        hrm.setObjectId(p.getObjectId());
        return hrm.getObjectId();
    }

    @Override
    public UUID convertToHRManager(IEmployee employee, UUID id)
    {
        String username = employee.getFirstName().charAt(0) + employee.getLastName();
        HRManager hrm = new HRManager(employee.getFirstName(), employee.getLastName(), username);
        hrm.setObjectId(id);
        return hrm.getObjectId();
    }

    @Override
    public UUID createOwner(Person p)
    {
        String username = p.getFirstName().charAt(0) + p.getLastName();
        Owner o = new Owner(p.getFirstName(), p.getLastName(), username);
        o.setObjectId(p.getObjectId());
        return o.getObjectId();
    }

    @Override
    public UUID convertToOwner(IEmployee employee, UUID id)
    {
        String username = employee.getFirstName().charAt(0) + employee.getLastName();
        Owner o = new Owner(employee.getFirstName(), employee.getLastName(), username);
        o.setObjectId(id);
        return o.getObjectId();
    }

    @Override
    public UUID createCustomer(Person p) {
        Customer c = new Customer(p.getFirstName(), p.getLastName());
        c.setObjectId(p.getObjectId());
        return c.getObjectId();
    }

    @Override
    public UUID createBankAccount(Customer c) {
        BankAccount ba = new BankAccount();
        c.addAccount(ba.getObjectId());
        return ba.getObjectId();
    }

    @Override
    public UUID createCreditAccount(Customer c) {
        CreditAccount ca = new CreditAccount();
        c.addAccount(ca.getObjectId());
        return ca.getObjectId();
    }

    public UUID fireEmployee(UUID employee_id)
    {
        //TODO...
        return employee_id;
    }

    /**
     * Changes the position of the existing employee.
     * @param employee_id UUID of the existing employee
     * @param position position the employee will have
     * @return UUID of the promoted employee
     * @throws IllegalStateException if position is not a valid position
     */
    public UUID promoteEmployee(UUID employee_id, String position) throws IllegalStateException
    {
        IEmployee existing_employee = this.getEmployee(employee_id);

        fireEmployee(employee_id);

        switch (position) {
            case "Teller":
                convertToTeller(existing_employee, employee_id);
                break;
            case "Loan Officer":
                convertToLoanOfficer(existing_employee, employee_id);
                break;
            case "Manager":
                convertToManager(existing_employee, employee_id);
                break;
            case "HR Manager":
                convertToHRManager(existing_employee, employee_id);
                break;
            case "Owner":
                convertToOwner(existing_employee, employee_id);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return employee_id;
    }
}
