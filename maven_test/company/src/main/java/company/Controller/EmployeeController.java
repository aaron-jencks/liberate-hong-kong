package company.Controller;

import company.Controller.Abstract.AEmployeeController;


public class EmployeeController extends AEmployeeController {

    private static EmployeeController controllerInstance = null;
    
    /**
     * Create the instance of the singleton
     * @return EmployeeController
     */
    public static EmployeeController getInstance(){
        if(controllerInstance == null){
            createTable();
            controllerInstance = new EmployeeController();
        }
        return controllerInstance;
    }

}
