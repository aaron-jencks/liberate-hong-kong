package company.Controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Controller.Abstract.ACustomerController;
import company.Controller.Abstract.ASQLController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Enum.AccountType;

public class CustomerController extends ACustomerController {

    private static CustomerController controllerInstance = null;
    

    /**
     * Make this controller a singleton
     * @return
     */
    public static CustomerController getInstance(){
        if (controllerInstance == null) {
            createTable();
            controllerInstance = new CustomerController();
        }
        return controllerInstance;
    }

}
