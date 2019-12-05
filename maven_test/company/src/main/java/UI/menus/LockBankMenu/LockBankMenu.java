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

        // If bank is already locked - throw error (because I seemingly can't not show the option in the main menu)
        if (BankLock.getInstance().isBankLocked() == true) {
            toast("Cannot lock the bank because the bank is already locked.");
            return new ExitItem();
        }

        if ( !prompt_yesNo(prompt)) return new ExitItem();

        BankLock.getInstance().lockBank();
        invalidate();

        return new ExitItem();
    }
}