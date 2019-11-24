package UI.menus.PromoteMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;
import company.Entity.Employee;
import company.Entity.Enum.Position;

import java.util.Scanner;

public class PromoteMenu extends AMenu {

    public PromoteMenu(ITermController parent)
    {
        super(parent);
    }

    @Override
    public String get_display_string()
    {
        String result = "";

        // Add vertical padding
        int v_pad = get_y_coord(), h_pad = get_x_coord();
        for(int i = 0; i < v_pad; i++)
            result += "\n";

        String new_prompt = "";
        for(int i = 0; i < h_pad; i++)
            new_prompt += " ";

        return new_prompt;
    }

    @Override
    public IMenuItem prompt()
    {

        Scanner sc = new Scanner(System.in);
        String confirm = "";

        if(!is_valid)
            display();

        String employee_id_str = "";
        String position = "";
        while(true) {
            String padding = get_display_string();

            while (confirm.toUpperCase().indexOf('Y') < 0) {
                try {
                    employee_id_str = UIUtil.get_input(sc, employee_id_str, padding + "Employee ID: ", (String s) -> true);
                    position = UIUtil.get_input(sc, position, padding + "Position: ", (String s) -> { return true;});

                    confirm = UIUtil.get_input(sc, confirm, padding + "Confirm changing this employee's position? ", (String s) -> { return true;});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //try to get the employee
            Employee employee = EmployeeController.getInstance().getEmployee(employee_id_str);
            if(employee == null){
                try {
                    UIUtil.get_input(sc, confirm, padding + "Employee id " + employee_id_str + " is not a valid employee id. Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
                return new ExitItem(parent);
            }
            //check if it's a valid position
            if(Position.valueOf(position.toUpperCase()) == null){
                try {
                    UIUtil.get_input(sc, confirm, padding + "No position found of type " + position + " Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
                return new ExitItem(parent);
            }
            //update their position
            employee.setPosition(Position.valueOf(position.toUpperCase()));

            return new ExitItem(parent);
        }

    }
}
