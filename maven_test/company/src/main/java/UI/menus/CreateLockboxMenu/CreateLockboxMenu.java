package UI.menus.CreateLockboxMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class CreateLockboxMenu extends AMenu {
    private String description = "";
    private String password = "";

    public CreateLockboxMenu(ITermController parent) {
        super(parent);
    }

    @Override
    public int get_y_coord() {
        return (parent.get_term_height() - 2) >>1;
    }

    @Override
    public String get_display_string() {
        String result = new String();

        int v_pad = get_y_coord(), h_pad = get_x_coord();
        for(int i = 0; i < v_pad; i++)
            result += "\n";

        String new_prompt = new String();
        for(int i = 0; i < h_pad; i++)
            new_prompt += " ";

        String s = "";
        if(description.isEmpty()){
            s = "What is being put in the lock box? ";
        }else if(password.isEmpty()){
            s = "Lockbox passcode: ";
        }

        prompt = new_prompt + s;
        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);
        if(!is_valid)
            display();
        String confirm = "";

        while(confirm.toUpperCase().indexOf('Y') < 0){
            description = "";
            password = "";

            try {
                get_display_string();
                description = UIUtil.get_input(sc, description, prompt, (String s) -> true);

                get_display_string();
                password = UIUtil.get_input(sc, password, prompt, (String s) -> true);

                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, prompt + "Confirm create lockbox? y/n ", (String s) -> true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        get_display_string();
        Lockbox box = LockboxController.getInstance().createLockbox(description, password);


        try {
            UIUtil.get_input(sc, confirm, "\n" + prompt + "Lockbox created.\n" + prompt + "Lockbox id = " + box.getId().toString()
                    + "\n" + prompt + "Press any key to return to the menu.", (String s) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(parent);
    }
    
}