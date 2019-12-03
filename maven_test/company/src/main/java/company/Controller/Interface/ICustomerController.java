package company.Controller.Interface;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Enum.AccountType;
import company.exceptions.BankLockedException;

public interface ICustomerController{

    static String TABLE_NAME = "CUSTOMER";

    static String CUST_FIRSTNAME_CONST = "first";
    static String CUST_LASTNAME_CONST = "last";
    static String CUST_ACCOUNTS_CONST = "accounts";

    /**
     * Get customer by id
     * @param id
     * @return
     */
    public Customer getCustomer(UUID id);
    
    /**
     * Add an account to the customer
     * @param customer
     * @param type
     * @return
     */
    public Account addAccount(Customer customer, AccountType type) throws BankLockedException;

    /**
     * Create a new customer based on their name
     * @param first
     * @param last
     * @return
     */
    public Customer createCustomer(String first, String last);

    /**
     * Create a customer from an existing person
     * @param Person
     * @return
     */
    public Customer createCustomer(Person Person);

    /**
     * Get a list of all the customers
     * @return
     */
    public ArrayList<Customer> getAll();

}