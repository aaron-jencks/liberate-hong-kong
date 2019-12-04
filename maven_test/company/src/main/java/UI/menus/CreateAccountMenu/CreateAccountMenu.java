package UI.menus.CreateAccountMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
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

public class CreateAccountMenu extends AMenu {

    private String firstName = new String();
    private String lastName = new String();
    private String accept = new String();

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

        try {
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            firstName = UIUtil.get_input(sc, firstName, "", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            lastName = UIUtil.get_input(sc, lastName, "", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        //TODO, add functionality to search for customer before creating a new one
        Person p = PersonController.getInstance().createPerson(firstName, lastName);
        Customer c = CustomerController.getInstance().createCustomer(p);
        Account a = CustomerController.getInstance().addAccount(c, AccountType.SAVINGS);
        String accountNumber = a.getId().toString();

        toast("Account created. Account id = " + accountNumber);

        return new ExitItem();
    }
}