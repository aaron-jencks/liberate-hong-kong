package company;

import java.io.IOException;
import java.util.UUID;

import UI.AMenu;
import UI.IMenuItem;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import UI.menus.GreeterMenu.GreeterMenu;
import company.Controller.EmployeeController;
import company.Entity.Person;
import company.Entity.Vault;
import company.Entity.Interface.IEmployee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        TermController term = new TermController();
        Vault vault = Vault.getInstance();

        UUID user_id = vault.createTeller(new Person("John", "Smith"));
        IEmployee t = vault.getEmployee(user_id);
        t.setEmployeePassword("p");
        t.setEmployeeUsername("u");
        t.setEmployeeSecurityQuestion("Hello?");
        t.setEmployeeSecurityAnswer("World!");
        // TODO vault.save();

        EmployeeController employeeController = new EmployeeController(vault);

        GreeterMenu splash = new GreeterMenu(term, employeeController);
        term.set_main_window(splash);

        while(true)
        {
            IMenuItem response = term.interact();
            AMenu next_window = response.activate();
            if(next_window != null)
                term.set_main_window(next_window);
            else if(response instanceof ExitItem)
            {
                // term.close_window();
                if(term.get_window_count() == 0)
                    break;
            }
        }
    }
}
