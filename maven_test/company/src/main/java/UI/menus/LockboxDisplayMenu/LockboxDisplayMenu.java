package UI.menus.LockboxDisplayMenu;

import UI.menus.ADisplayMenu;
import company.Controller.LockboxController;

public class LockboxDisplayMenu extends ADisplayMenu {
    public LockboxDisplayMenu()
    {
        super(LockboxController.getInstance().getAll());
        title = "Current Lockbox Directory";
    }
}