package UI.menus.UnlockBankMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.global_menu_items.ExitItem;
import company.Entity.BankLock;

public class UnlockBankMenu extends AMenu {

    @Override
    public String get_display_string() {
        String result = new String();

        // Add vertical padding
        int v_pad = get_y_coord(), h_pad = get_x_coord();
        for (int i = 0; i < v_pad; i++)
            result += "\n";

        String new_prompt = new String();
        for (int i = 0; i < h_pad; i++)
            new_prompt += " ";

        String s = "Do you want to unlock the bank (allowing account based operations)?";

        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        if (!is_valid)
            display();

        if(!prompt_yesNo(prompt)) return new ExitItem();

        BankLock.getInstance().unlockBank();

        return new ExitItem();
    }
}