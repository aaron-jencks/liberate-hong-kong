package company.Controller;

import company.Controller.Abstract.APersonController;

public class PersonController extends APersonController{

    private static PersonController controllerInstance = null;


    /**
     * Make this controller a singleton
     * @return
     */
    public static PersonController getInstance(){
        if (controllerInstance == null) {
            createTable();
            controllerInstance = new PersonController();
        }
        return controllerInstance;
    }

}
