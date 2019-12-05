package UI.menus.ForgotPasswordMenu;

import java.util.Scanner;

import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.global_menu_items.ExitItem;
import UI.menus.LoginMenu.LoginMenu;
import company.Controller.EmployeeController;
import company.Entity.Employee;
import company.exceptions.EmployeeNotFoundException;

public class ForgotPasswordMenu extends LoginMenu {
    protected String security_question = new String();
    protected String security_answer = new String();
    protected String confirm_password = new String();
    protected int iteration = 0;

    @Override
    public IMenuItem prompt()
    {
        if(ForgotPasswordMenu.inputScanner == null)
            ForgotPasswordMenu.inputScanner = new Scanner(System.in);

        if(!is_valid)
            display();

        try { 
            EmployeeController employeeController = EmployeeController.getInstance();
            AnsiUtil.append_display_string(get_x_coord(), "Username: ");
            username = UIUtil.get_input(ForgotPasswordMenu.inputScanner, username, "", (String s) -> { return true; }); 
            Employee employee = employeeController.findByUsername(username);

            security_question = employee.getQuestion() + '\n' + "Answer? "; // Shouldn't be null, so no need to check
            AnsiUtil.append_display_string(get_x_coord(), "Security Question: " + security_question);
            try { security_answer = UIUtil.get_input(ForgotPasswordMenu.inputScanner, security_answer, 
                                                     "", (String s) -> { return true; }); }
            catch(Exception e) { e.printStackTrace(); }

            if(!employee.getAnswer().equals(security_answer))
            {
                toast("Account has been locked down due to suspicious activity, please contact your system administrator.");
                return new ExitItem();
            }

            do
            {
                AnsiUtil.append_display_string(get_x_coord(), "New Password: ");
                try { password = UIUtil.get_input(ForgotPasswordMenu.inputScanner, password, "", (String s) -> { return true; }); }
                catch(Exception e) { e.printStackTrace(); }

                AnsiUtil.append_display_string(get_x_coord(), "Confirm Password: ");
                try { confirm_password = UIUtil.get_input(ForgotPasswordMenu.inputScanner, confirm_password, "", (String s) -> { return true; }); }
                catch(Exception e) { e.printStackTrace(); }

                if(!password.equals(confirm_password))
                    AnsiUtil.append_display_string(get_x_coord(), "Those passwords don't match, try again.");

            } while(!password.equals(confirm_password));

            employee.setPassword(password);

            toast("Account password changed successfully.");
        }
        catch(EmployeeNotFoundException e) {
            toast("That user does not exist, please contact your system administrator to create a new account.");
            return new ExitItem();
        }
        catch(Exception e) { e.printStackTrace(); }

        return new ExitItem();
    }
}