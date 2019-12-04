package UI.menus.EditLockboxMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class EditLockboxMenu extends AMenu {
    private String id = "";
    private String password = "";
    private String description = "";
    private static Lockbox box = null;

    @Override
    public int get_y_coord() {
        return (TermController.get_instance().get_term_height() - 2) >>1;
    }

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "";
        if(id.isEmpty()){
            s = "Lockbox id: ";
        }else if(password.isEmpty()){
            s = "The box contains : " + box.getDescription() + " What is being put in the lock box? ";
        }

        prompt = s;
        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);
        if(!is_valid)
            display();

        id = "";
        password = "";
        description = "";

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            id = UIUtil.get_input(sc, id, "", (String s) -> true);

            get_display_string();
            box = LockboxController.getInstance().getLockbox(id);
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            password = UIUtil.get_input(sc, password, "", (String s) -> true);
            //check password

            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            description = UIUtil.get_input(sc, description, "", (String s) -> true);
            //update desc

            get_display_string();
            if(!prompt_yesNo("Confirm create lockbox?")) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        toast("Lockbox created.\nLockbox id = " + box.getId().toString());

        return new ExitItem();
    }
    
}