package company.Entity.Interface;

import java.util.UUID;

import company.Entity.Customer;
import company.Entity.Person;

public interface IVault extends ISaveable
{
    /**
     * Finds an employee using the given person as a search parameter
     * @param p Person to check if they are an employee
     * @return Returns the found employee, or null if it wasn't found.
     */
    public IEmployee getEmployee(Person p);

    /**
     * Finds an employee by using their UUID as the search parameter
     * @param employee_id UUID of the employee
     * @return Returns the found employee, or null if it wasn't found.
     */
    public IEmployee getEmployee(UUID employee_id);

    /**
     * Finds an employee using their username as a search parameter
     * @param username username of the employee
     * @return Returns the found employee, or null if it wasn't found.
     */
    public IEmployee getEmployee(String username);

    /**
     * Finds a customer using the given person as a search parameter
     * @param p Person to check if they are a customer
     * @return Returns the found customer, or null if it wasn't found.
     */
    public ICustomer getCustomer(Person p);

    /**
     * Finds a customer using the given customer's UUID as a search parameter
     * @param customerId UUID of the customer
     * @return Returns the found customer, or null if it wasn't found.
     */
    public ICustomer getCustomer(UUID customerId);

    /**
     * Creates a new Teller from the given person
     * @param p Person to make an new employee as a teller
     * @return returns the UUID to the new Teller
     */
    public UUID createTeller(Person p);

    /**
     * Converts a given employee to a Teller
     * @param employee existing Employee that will be converted to a Teller
     * @param id UUID to assign to the Teller
     * @return returns the UUID that was passed in.
     */
    public UUID convertToTeller(IEmployee employee, UUID id);

    /**
     * Creates a new Load Officer from the given person
     * @param p
     * @return returns the UUID to the new Loan Officer
     */
    public UUID createLoanOfficer(Person p);

    /**
     * Converts the given employee into a Loan Officer
     * @param employee
     * @param id UUID to assign to the Loan Officer
     * @return returns the UUID that was passed in.
     */
    public UUID convertToLoanOfficer(IEmployee employee, UUID id);

    /**
     * Creates a new Manager from the given person
     * @param p
     * @return returns the UUID to the new Manager
     */
    public UUID createManager(Person p);

    /**
     * Converts the given employee into a Manager
     * @param employee
     * @param id UUID to assign to the Manager
     * @return returns the UUID that was passed in.
     */
    public UUID convertToManager(IEmployee employee, UUID id);

    /**
     * Creates a new HR Manager from the given person
     * @param p
     * @return Returns the UUID to the new HR Manager
     */
    public UUID createHRManager(Person p);

    /**
     * Converts the given employee into an HR Manager
     * @param employee
     * @param id UUID to assign to the HR Manager
     * @return returns the UUID that was passed in.
     */
    public UUID convertToHRManager(IEmployee employee, UUID id);

    /**
     * Creates a new Owner from the given person
     * @param p
     * @return returns the UUID to the new Person
     */
    public UUID createOwner(Person p);

    /**
     * Converts the given employee into an Owner
     * @param employee
     * @param id UUID to assign to the Owner
     * @return returns the UUID that was passed in.
     */
    public UUID convertToOwner(IEmployee employee, UUID id);

    /**
     * Creates a new Customer using the given Person
     * @param p
     * @return returns the UUID to the new Person
     */
    public UUID createCustomer(Person p);

    /**
     * Creates a new bank account and assigns it to the given customer
     * @param c
     * @return returns the UUID assigned to the new bank account
     */
    public UUID createBankAccount(Customer c);

    /**
     * Creates a new credit account and assigns it to the given customer
     * @param c
     * @return returns the UUID assigned to the new credit account
     */
    public UUID createCreditAccount(Customer c);

}
