package UI.menus.CreateLockboxMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.global_menu_items.ExitItem;
import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class CreateLockboxMenu extends AMenu {
    private String description = "";
    private String password = "";

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "";
        if(description.isEmpty()){
            s = "What is being put in the lock box? ";
        }else if(password.isEmpty()){
            s = "Lockbox password: ";
        }

        prompt = s;
        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);
        if(!is_valid)
            display();
        
        description = "";
        password = "";

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            description = UIUtil.get_input(sc, description, "", (String s) -> true);

            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            password = UIUtil.get_input(sc, password, "", (String s) -> true);

            get_display_string();
            if(!prompt_yesNo("Confirm create lockbox?")) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        Lockbox box = LockboxController.getInstance().createLockbox(description, password);

        toast("Lockbox created.\nLockbox id = " + box.getId().toString());

        return new ExitItem();
    }
    
}