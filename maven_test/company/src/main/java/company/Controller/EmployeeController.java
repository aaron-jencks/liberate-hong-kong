package company.Controller;

import company.Controller.Abstract.AEmployeeController;
import company.Entity.Employee;


public class EmployeeController extends AEmployeeController {

    private static EmployeeController controllerInstance = null;
    private static Employee loggedInEmployee = null;

    /**
     * Return the logged in employee
     * @return
     */
    public Employee auth(){
        return loggedInEmployee;
    }

    /**
     * Set the authorized employee
     * @param employee
     */
    public void setAuth(Employee employee){
        loggedInEmployee = employee;
    }

    /**
     * Create the instance of the singleton
     * @return EmployeeController
     */
    public static EmployeeController getInstance(){
        if(controllerInstance == null){
            createTable();
            System.out.println("CREATED CONTROLLER");
            controllerInstance = new EmployeeController();
        }
        return controllerInstance;
    }

}
