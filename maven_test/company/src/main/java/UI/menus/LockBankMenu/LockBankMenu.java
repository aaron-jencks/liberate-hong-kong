package UI.menus.LockBankMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.global_menu_items.ExitItem;
import company.Entity.BankLock;

public class LockBankMenu extends AMenu {

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "Do you want to lock the bank (Preventing any account based operations)?";

        prompt = s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        if (!is_valid)
            display();

        if(!prompt_yesNo(prompt)) return new ExitItem();

        BankLock.getInstance().lockBank();
        invalidate();

        return new ExitItem();
    }
}