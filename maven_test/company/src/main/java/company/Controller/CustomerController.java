package company.Controller;

import company.Controller.Abstract.ACustomerController;

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
