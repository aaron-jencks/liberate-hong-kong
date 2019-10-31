package company;

import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import UI.menus.GreeterMenu.GreeterMenu;
import company.Entity.Controller.EmployeeController;
import company.Entity.Teller;
import UI.IMenuItem;
import UI.AMenu;
import company.Entity.Vault;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        Teller t = new Teller("John", "Smith", "TSmith", 0);
        t.setEmployeePassword("p");
        t.setEmployeeUsername("u");
        t.setEmployeeSecurityQuestion("Hello?");
        t.setEmployeeSecurityAnswer("World!");
        t.save();
        

        TermController term = new TermController();
        Vault vault = new Vault();
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
