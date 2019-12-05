package UI.menus.PromoteMenu;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;
import company.Entity.Employee;
import company.Entity.Enum.Position;

import java.util.Scanner;

public class PromoteMenu extends AMenu {

    @Override
    public IMenuItem prompt()
    {

        Scanner sc = new Scanner(System.in);

        if(!is_valid)
            display();

        String employee_id_str = "";
        String position = "";
        while(true) {

            try {
                AnsiUtil.append_display_string(get_x_coord(), "Employee ID: ");
                employee_id_str = UIUtil.get_input(sc, employee_id_str, "", (String s) -> true);
                AnsiUtil.append_display_string(get_x_coord(), "Position: ");
                position = UIUtil.get_input(sc, position, "", (String s) -> { return true;});

                if(!prompt_yesNo("Confirm changing this employee's position?"))
                    return new ExitItem();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //try to get the employee
            Employee employee = EmployeeController.getInstance().getEmployee(employee_id_str);
            if(employee == null){
                toast("Employee id " + employee_id_str + " is not a valid employee id.");
                invalidate();
                return new ExitItem();
            }
            //check if it's a valid position
            if(Position.valueOf(position.toUpperCase()) == null){
                toast("No position found of type " + position);
                invalidate();
                return new ExitItem();
            }
            //update their position
            employee.setPosition(Position.valueOf(position.toUpperCase()));

            return new ExitItem();
        }

    }
}
