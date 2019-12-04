package UI.menus.CloseCreditAccountMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.CustomerController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Enum.AccountType;

import java.util.Scanner;

public class CloseCreditAccountMenu extends AMenu {

    private String acctNum;

    public CloseCreditAccountMenu(ITermController parent) {
        super(parent);
    }

    @Override
    public int get_y_coord() {
        return (parent.get_term_height() - 2) >> 1;
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

        prompt = new_prompt + "Account Number: ";

        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();
        String confirm = "";

        int h_pad = get_x_coord();
        String h_space = new String();
        for (int i = 0; i < h_pad; i++)
            h_space += " ";

        while (confirm.toUpperCase().indexOf('Y') < 0) {

            try {
                get_display_string();
                acctNum = UIUtil.get_input(sc, acctNum, prompt, (String s) -> true);

                // TODO Display the current balance

                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, h_space + "Confirm closing this credit account? (y/N) ", (String s) -> true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // TODO verify that the account exists

        try {
            UIUtil.get_input(sc, confirm, "\n" + h_space + "Account closed.\n" + 
                             h_space + "Press any key to return to the account menu.", (String s) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(this.parent);
    }
}