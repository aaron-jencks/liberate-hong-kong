package UI.menus.UnlockBankMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.global_menu_items.ExitItem;
import company.Entity.BankLock;

public class UnlockBankMenu extends AMenu {

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "Do you want to unlock the bank (allowing account based operations)?";

        prompt = s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        if (!is_valid)
            display();

        // If bank is already unlocked - throw error (because I seemingly can't not show the option in the main menu)
        if (BankLock.getInstance().isBankLocked() == false) {
            toast("Cannot unlock the bank because the bank is already unlocked.");
            return new ExitItem();
        }

        if(!prompt_yesNo(prompt)) return new ExitItem();

        BankLock.getInstance().unlockBank();

        return new ExitItem();
    }
}