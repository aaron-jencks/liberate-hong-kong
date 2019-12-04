package UI.menus.CloseCreditAccountMenu;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.AccountController;
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
        
        try {
            get_display_string();

            while(true)
            {
                AnsiUtil.append_display_string(get_x_coord(), "Account Number: ");
                acctNum = UIUtil.get_input(sc, acctNum, "", (String s) -> true);

                // verify that the account exists
                Account acct = AccountController.getInstance().getAccount(acctNum);
                if(acct == null)
                {
                    toast("That account doesn't exist!");
                    if(!prompt_yesNo("Try again?"))
                        return new ExitItem(parent);
                    continue;
                }
                else if(acct.getType() != AccountType.CREDIT)
                {
                    toast("That is an invalid account type!");
                    if(!prompt_yesNo("Try again?")) 
                        return new ExitItem(parent);
                    continue;
                }

                // Display the current balance
                toast("Current account balance: $" + acct.getAmount());

                if(!prompt_yesNo("Confirm closing this credit account?"))
                    return new ExitItem(parent);

                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        toast("Account closed.");

        return new ExitItem(this.parent);
    }
}