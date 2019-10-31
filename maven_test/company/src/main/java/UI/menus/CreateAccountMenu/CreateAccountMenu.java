package UI.menus.CreateAccountMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import company.Entity.Customer;

public class CreateAccountMenu extends AMenu {

    private String firstName = new String();
    private String lastName = new String();

    public CreateAccountMenu(ITermController parent) {
        super(parent);
    }

    
    @Override
    public int get_y_coord()
    {
        return (parent.get_term_height() - 2) >> 1;
    }

    @Override
    public String get_display_string()
    {
        String result = new String();

        // Add vertical padding
        int v_pad = get_y_coord(), h_pad = get_x_coord();
        for(int i = 0; i < v_pad; i++)
            result += "\n";

        String new_prompt = new String();
        for(int i = 0; i < h_pad; i++)
            new_prompt += " ";

        String s = "";
        if(firstName.isEmpty()){
            s = "First Name: ";
        }
        else if(lastName.isEmpty()){
            s = "Last Name: ";
        }
        
        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if(!is_valid)
            display();

        try { firstName = UIUtil.get_input(sc, firstName, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();

        try { lastName = UIUtil.get_input(sc, lastName, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();


        Customer c = new Customer();
        c.setFirstName(firstName)
        .setLastName(lastName);
        // c.save();

        // String accountId = c.createAccount();

        return null;
    }
}