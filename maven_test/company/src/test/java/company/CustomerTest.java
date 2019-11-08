package company;

import org.junit.After;
import org.junit.Test;

import company.Entity.Customer;
import company.Entity.Interface.ISaveable;

public class CustomerTest{

    @After
    public void cleanUp(){
        ISaveable.clearFile(Customer.class.getName());
    }


    @Test
    public void CustomerPersonTest(){
        Customer c = new Customer("first", "last");
        
    }

}