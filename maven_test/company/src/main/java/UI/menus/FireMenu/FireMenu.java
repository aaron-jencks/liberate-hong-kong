package UI.menus.FireMenu;

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

public class FireMenu extends AMenu {
    private static String employeeId = new String();
    private static String confirm = new String();
    private static String done = new String();
    private Employee employee;

    @Override
    public String get_display_string()
    {
        String result = new String();

        String s = "";
        if(employeeId.isBlank()){
            s = "Enter the employeeId of the employee you want to fire: ";
        }else if(confirm.isBlank()){
            s = "Are you sure you want to fire " + employee.getFirstName() + " ?";
        }

        prompt = s;

        return result;
    }

    @Override
    public IMenuItem prompt() {

        if(EmployeeController.getInstance().auth().getPosition() != Position.HR){
            //only HR can fire
            return new ExitItem();
        }
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        try {
            get_display_string();
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            employeeId = UIUtil.get_input(sc, employeeId, "", (String s) -> {
                return true;
            });
            employee = EmployeeController.getInstance().getEmployee(employeeId);
            get_display_string();
            if(!(prompt_yesNo(prompt))) return new ExitItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmployeeController.getInstance().deleteEmployee(employee);
        get_display_string();

        toast("Employee Fired.");
        return new ExitItem();
    }
}
