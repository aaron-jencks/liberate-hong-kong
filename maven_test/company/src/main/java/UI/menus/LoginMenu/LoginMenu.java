package UI.menus.LoginMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AlignmentType;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.LoginMenu.items.MainMenuItem;
import company.Controller.EmployeeController;
import company.Entity.Employee;
import company.exceptions.EmployeeNotFoundException;

public class LoginMenu extends AMenu {
    protected Employee employee;
    protected String username = new String();
    protected String password = new String();

    public LoginMenu(ITermController parent)
    {
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
        prompt = new_prompt + ((username.isEmpty()) ? "Username: " : "Password: ");

        return result;
    }

    @Override
    public IMenuItem prompt()
    {
        if(LoginMenu.inputScanner == null)
            LoginMenu.inputScanner = new Scanner(System.in);

        if(!is_valid)
            display();

        try { 
            username = UIUtil.get_input(LoginMenu.inputScanner, username, prompt, (String s) -> { return true; }); 
            employee = EmployeeController.getInstance().findByUsername(username);

            if(employee == null){
                //no employee found with that username
            }

            get_display_string();

            try { password = UIUtil.get_input(LoginMenu.inputScanner, password, prompt, (String s) -> { return true; }); }
            catch(Exception e) { e.printStackTrace(); }

            boolean validUP = employee.getPassword().equals(password);
            if(validUP){
                EmployeeController.getInstance().setAuth(employee);
                return new MainMenuItem(parent);
            }
        }
        catch(EmployeeNotFoundException e) {
            System.out.println(UIUtil.pad_string("That user does not exist, please contact your system administrator to create a new account.", 
                                                 parent.get_term_width(), AlignmentType.center));
            System.out.println(UIUtil.pad_string("Press RETURN to continue", 
                                                 parent.get_term_width(), AlignmentType.center));
            try { UIUtil.get_input(LoginMenu.inputScanner, new String(), "", (String s) -> { return true; }); }
            catch(Exception e1) { e1.printStackTrace(); }
        }
        catch(Exception e) { e.printStackTrace(); }

        return new ExitItem(parent);
        
    }
}