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
        }else if(confirm.isBlank()){
            s = "Confirm hiring?";
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

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            firstName = UIUtil.get_input(sc, firstName, "", (String s) -> {
                return true;
            });
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            lastName = UIUtil.get_input(sc, lastName, "", (String s) -> {
                return true;
            });
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            position = UIUtil.get_input(sc, position, "", (String s) -> {
                //TODO need to validate
                try {
                    Position.valueOf(position);
                } catch (IllegalArgumentException e) {
                    position = new String();
                    return false;
                }
                return true;
            });
            get_display_string();
            if(!prompt_yesNo(prompt)) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        get_display_string();

        Employee e = EmployeeController.getInstance()
            .createEmployee(Position.valueOf(position.toUpperCase()), firstName, lastName);

        toast("Employee Hired. Employee id = " + e.getId().toString());
        return new ExitItem();
    }
}
