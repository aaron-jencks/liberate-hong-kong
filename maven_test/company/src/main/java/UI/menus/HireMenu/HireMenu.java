package UI.menus.HireMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;
import company.Entity.Employee;
import company.Entity.Enum.Position;

public class HireMenu extends AMenu {
    private String firstName = new String();
    private String lastName = new String();
    private String position = new String();
    private String username = new String();
    private String password = new String();
    private String question = new String();
    private String answer = new String();
    private String confirm = new String();
    private String done = new String();

    @Override
    public String get_display_string()
    {
        String result = new String();

        String s = "";
        if(firstName.isBlank()){
            s = "First Name: ";
        } else if(lastName.isBlank()){
            s = "Last Name: ";
        }else if(position.isBlank()){
            s = "Position: ";
        }else if(username.isBlank()){
                s = "Username: ";
        }else if(password.isBlank()){
            s = "Password: ";
        }else if(question.isBlank()){
            s = "Recovery Question: ";
        }else if(answer.isBlank()){
            s = "Recovery Answer: ";
        }else if(confirm.isBlank()){
            s = "Confirm hiring? ";
        }

        prompt = s;

        return result;
    }

    @Override
    public IMenuItem prompt() {

        if(EmployeeController.getInstance().auth().getPosition() != Position.HR){
            //only HR can hire
            return new ExitItem();
        }
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        while (confirm.toUpperCase().indexOf('Y') < 0) {
            try {
                get_display_string();
                firstName = UIUtil.get_input(sc, firstName, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                lastName = UIUtil.get_input(sc, lastName, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                position = UIUtil.get_input(sc, position, prompt, (String s) -> {
                    try {
                        Position.valueOf(s.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        position = new String();
                        return false;
                    }
                    return true;
                });
                get_display_string();
                username = UIUtil.get_input(sc, username, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                password = UIUtil.get_input(sc, password, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                question = UIUtil.get_input(sc, question, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                answer = UIUtil.get_input(sc, answer, prompt, (String s) -> {
                    return true;
                });

                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, prompt, (String s) -> {
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        get_display_string();

        Employee e = EmployeeController.getInstance()
            .createEmployee(Position.valueOf(position.toUpperCase()), question, answer, username, password, firstName, lastName);

        toast("Employee Hired. Employee id = " + e.getId().toString());
        return new ExitItem();
    }
}
