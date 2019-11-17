package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONArray;

import company.Entity.Customer;
import company.Entity.Person;

public interface IVault
{
    public JSONArray getEmployees();
    public JSONArray getCustomers();
    public JSONArray getAccounts();

    public IAccount getAccount(UUID accountId);

    public IEmployee getEmployee(Person p);
    public IEmployee getEmployee(UUID employee_id);
    public IEmployee getEmployee(String username);

    public ICustomer getCustomer(Person p);
    public ICustomer getCustomer(UUID customerId);

    public UUID createTeller(Person p);
    public UUID convertToTeller(IEmployee employee, UUID id);
    public UUID createLoanOfficer(Person p);
    public UUID convertToLoanOfficer(IEmployee employee, UUID id);
    public UUID createManager(Person p);
    public UUID convertToManager(IEmployee employee, UUID id);
    public UUID createHRManager(Person p);
    public UUID convertToHRManager(IEmployee employee, UUID id);
    public UUID createOwner(Person p);
    public UUID convertToOwner(IEmployee employee, UUID id);

    public UUID createCustomer(Person p);
    public UUID createBankAccount(Customer c);
    public UUID createCreditAccount(Customer c);

}
