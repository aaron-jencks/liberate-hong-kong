package UI.menus.CreateCreditAccountMenu;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import company.Controller.CustomerController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Enum.AccountType;
import company.exceptions.BankLockedException;

import java.util.Scanner;

public class CreateCreditAccountMenu extends AMenu {

    private String firstName = "";
    private String lastName = "";

    @Override
    public int get_y_coord() {
        return (TermController.get_instance().get_term_height() - 2) >> 1;
    }

    @Override
    public String get_display_string() {
        String result = new String();

        String s = "";
        if (firstName.isEmpty()) {
            s = "First Name: ";
        } else if (lastName.isEmpty()) {
            s = "Last Name: ";
        }

        prompt = s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        firstName = "";
        lastName = "";

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            firstName = UIUtil.get_input(sc, firstName, "", (String s) -> true);

            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            lastName = UIUtil.get_input(sc, lastName, "", (String s) -> true);

            get_display_string();
            if(!prompt_yesNo("Confirm creating this credit account?")) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        //TODO, fix to first search for customer before creating a new one
        Customer c;
        Account a;

        try {
            c = CustomerController.getInstance().createCustomer(firstName, lastName);
            a = CustomerController.getInstance().addAccount(c, AccountType.CREDIT);
        }
        catch (BankLockedException e) {
            toast("Cannot create the account because the bank is locked.");
            return new ExitItem();
        }

        toast("Account created.\nAccount id = " + a.getId().toString());

        return new ExitItem();
    }
}