package company;

import java.io.IOException;

import UI.AMenu;
import UI.IMenuItem;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import UI.menus.GreeterMenu.GreeterMenu;
import company.Controller.EmployeeController;
import company.Controller.PersonController;
import company.Entity.Employee;
import company.Entity.Enum.Position;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        TermController term = new TermController();

        PersonController.getInstance().createPerson("test", "test");
        
        EmployeeController e = EmployeeController.getInstance();
        Employee teller = e.findByUsername("u");
        if(teller == null){
            e.createEmployee(Position.TELLER, "Hello?", "World!", "u", "p", "John", "Smith");
        }
        Employee loanOff = e.findByUsername("ul");
        if(loanOff == null){
            e.createEmployee(Position.LOAN_OFFICER, "Hello?", "World!", "ul", "p", "JohnLoan", "Smith");
        }
        Employee hr = e.findByUsername("uhr");
        if(hr == null){
            e.createEmployee(Position.HR, "Hello?", "World!", "uhr", "p", "JohnHR", "Smith");
        }
        Employee m = e.findByUsername("um");
        if(m == null){
            e.createEmployee(Position.MANAGER, "Hello?", "World!", "um", "p", "JohnManager", "Smith");
        }

        GreeterMenu splash = new GreeterMenu(term);
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
