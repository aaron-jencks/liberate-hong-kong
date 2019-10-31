package UI.menus.LoginMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.LoginMenu.items.MainMenuItem;
import company.Entity.Abstract.AEmployee;
import UI.UIUtil;
import company.Entity.Controller.EmployeeController;

public class LoginMenu extends AMenu {
    protected String username = new String();
    protected String password = new String();

    public LoginMenu(ITermController parent, EmployeeController employeeController)
    {
        super(parent, employeeController);
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
        prompt = new_prompt + ((username.isEmpty()) ? "Username: " : "Password: ");

        return result;
    }

    @Override
    public IMenuItem prompt()
    {
        Scanner sc = new Scanner(System.in);

        if(!is_valid)
            display();

        try { username = UIUtil.get_input(sc, username, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();

        try { password = UIUtil.get_input(sc, password, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        // TODO check existence
        boolean validUP = AEmployee.CheckPassword(username, password);
        if(validUP){
            return new MainMenuItem(parent, this.employeeController);
        }

        return new ExitItem(parent);
        
    }
}