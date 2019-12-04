package UI.menus.UnlockBankMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.CustomerController;
import company.Controller.PersonController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Enum.AccountType;
import company.Entity.BankLock;
import company.exceptions.BankLockedException;

public class UnlockBankMenu extends AMenu {

    private String accept = new String();

    public UnlockBankMenu(ITermController parent) {
        super(parent);
    }

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

        String s = "Do you want to unlock the bank (allowing account based operations)? (y/N) ";

        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        try {
            accept = UIUtil.get_input(sc, accept, prompt, (String s) -> { return true; });
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (accept.toUpperCase().indexOf('Y') < 0) {
            return new ExitItem(this.parent);
        }

        BankLock.getInstance().unlockBank();

        return new ExitItem(this.parent);
    }
}