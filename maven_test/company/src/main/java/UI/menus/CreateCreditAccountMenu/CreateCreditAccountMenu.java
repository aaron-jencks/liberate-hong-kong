package UI.menus.CreateCreditAccountMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.CustomerController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Enum.AccountType;

import java.util.Scanner;
import java.util.UUID;

public class CreateCreditAccountMenu extends AMenu {

    private String firstName = "";
    private String lastName = "";

    public CreateCreditAccountMenu(ITermController parent) {
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

        String s = "";
        if (firstName.isEmpty()) {
            s = "First Name: ";
        } else if (lastName.isEmpty()) {
            s = "Last Name: ";
        }

        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();
        String confirm = "";

        while (confirm.toUpperCase().indexOf('Y') < 0) {
            firstName = "";
            lastName = "";

            try {
                get_display_string();
                firstName = UIUtil.get_input(sc, firstName, prompt, (String s) -> true);

                get_display_string();
                lastName = UIUtil.get_input(sc, lastName, prompt, (String s) -> true);

                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, prompt + "Confirm creating this credit account? (y/N) ", (String s) -> true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        get_display_string();
        //TODO, fix to first search for customer before creating a new one
        Customer c = CustomerController.getInstance().createCustomer(firstName, lastName);
        Account a = CustomerController.getInstance().addAccount(c, AccountType.CREDIT);

        try {
            UIUtil.get_input(sc, confirm, "\n" + prompt + "Account created.\n" + prompt + "Account id = " + a.getId().toString()
                    + "\n" + prompt + "Press any key to return to the account menu.", (String s) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(this.parent);
    }
}