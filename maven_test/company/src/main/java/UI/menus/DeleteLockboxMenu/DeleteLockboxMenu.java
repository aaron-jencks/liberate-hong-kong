package UI.menus.DeleteLockboxMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class DeleteLockboxMenu extends AMenu {
    private String id = new String();

    public DeleteLockboxMenu(ITermController parent) {
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
        if(id.isEmpty()){
            s = "Lockbox id: ";
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
            id = "";

            try {
                get_display_string();
                id = UIUtil.get_input(sc, id, prompt, (String s) -> true);

                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, prompt + "Confirm create lockbox? y/n ", (String s) -> true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        get_display_string();
        Lockbox box = LockboxController.getInstance().getLockbox(id);
        LockboxController.getInstance().deleteLockbox(box);

        try {
            UIUtil.get_input(sc, confirm, "\n" + prompt + "Lockbox closed.\n" + prompt + "Return the customer their items : " + box.getDescription()
                    + "\n" + prompt + "Press any key to return to the menu.", (String s) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(parent);
    }
    
}