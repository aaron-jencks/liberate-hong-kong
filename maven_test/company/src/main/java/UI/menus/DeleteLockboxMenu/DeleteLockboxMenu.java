package UI.menus.DeleteLockboxMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.global_menu_items.ExitItem;
import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class DeleteLockboxMenu extends AMenu {
    private String id = new String();
    private Lockbox box = null;

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "";
        if(id.isEmpty()){
            s = "Lockbox id: ";
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

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            id = UIUtil.get_input(sc, id, "", (String s) -> true);

            box = LockboxController.getInstance().getLockbox(id);
            if(box == null){
                toast("No lockbox found with that ID");
                return new ExitItem();
            }

            get_display_string();
            if(!prompt_yesNo("Confirm delete lockbox?")) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        
        LockboxController.getInstance().deleteLockbox(box);

        toast("Lockbox closed.\nReturn the customer their items : " + box.getDescription());

        return new ExitItem();
    }
    
}