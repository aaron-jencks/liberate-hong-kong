package UI.menus.CreateAccountMenu;

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
import company.exceptions.BankLockedException;

public class CreateAccountMenu extends AMenu {

    private String firstName = new String();
    private String lastName = new String();
    private String accept = new String();

    public CreateAccountMenu(ITermController parent) {
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

        try {
            firstName = UIUtil.get_input(sc, firstName, prompt, (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            lastName = UIUtil.get_input(sc, lastName, prompt, (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        //TODO, add functionality to search for customer before creating a new one
        Person p;
        Customer c;
        Account a;

        try {
            p = PersonController.getInstance().createPerson(firstName, lastName);
            c = CustomerController.getInstance().createCustomer(p);
            a = CustomerController.getInstance().addAccount(c, AccountType.SAVINGS);
        }
        catch (BankLockedException e) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + "Cannot create the account because the bank is locked.", (String s) -> { return true; });
            } catch (Exception er) {
                er.printStackTrace();
            }
            return new ExitItem(this.parent);
        }

        String accountNumber = a.getId().toString();

        try {
            accept = UIUtil.get_input(sc, accept, prompt + " Account created. Account id = " + accountNumber
                    + "\n Press any key to return to the account menu.", (String s) -> {
                        return true;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(this.parent);
    }
}