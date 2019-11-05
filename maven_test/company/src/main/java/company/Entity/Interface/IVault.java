package company.Entity.Interface;

import java.util.UUID;

import company.Entity.Person;

public interface IVault
{
    public IEmployee getEmployee(Person p);
    public IEmployee getEmployee(UUID employee_id);
    public IEmployee getEmployee(String username);
    public ICustomer getCustomer(Person p);
    public ICustomer getCustomer(UUID customerId);
    public UUID createTeller(Person p);
    public UUID createLoanOfficer(Person p);
    public UUID createManager(Person p);
    public UUID createHRManager(Person p);
    public UUID createOwner(Person p);
}
