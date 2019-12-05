package company.Controller;

import company.Controller.Abstract.ALockboxController;

public class LockboxController extends ALockboxController {
    private static LockboxController controllerInstance = null;

    /**
     * Singleton
     * @return
     */
    public static LockboxController getInstance(){
        if(controllerInstance == null){
            createTable();
            controllerInstance = new LockboxController();
        }
        return controllerInstance;
    }
}