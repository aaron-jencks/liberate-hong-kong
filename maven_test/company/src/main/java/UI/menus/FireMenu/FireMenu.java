package UI.menus.FireMenu;

import java.util.Scanner;

import UI.AMenu;
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

    public FireMenu(ITermController parent) {
        super(parent);
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

        String s = "";
        if(employeeId.isBlank()){
            s = "Enter the employeeId of the employee you want to fire: ";
        }else if(confirm.isBlank()){
            s = "Are you sure you want to fire " + employee.getFirstName() + " ?";
        }

        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {

        if(EmployeeController.getInstance().auth().getPosition() != Position.HR){
            //only HR can fire
            return new ExitItem(this.parent);
        }
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        while (confirm.toUpperCase().indexOf('Y') < 0) {
            try {
                get_display_string();
                employeeId = UIUtil.get_input(sc, employeeId, prompt, (String s) -> {
                    return true;
                });
                employee = EmployeeController.getInstance().getEmployee(employeeId);
                get_display_string();
                confirm = UIUtil.get_input(sc, confirm, prompt, (String s) -> {
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        EmployeeController.getInstance().deleteEmployee(employee);
        get_display_string();

        try {
            done = UIUtil.get_input(sc, done, prompt + " Employee Fired."
                    + "\n Press any key to return to the account menu.", (String s) -> {
                        return true;
                    });
        } catch (Exception er) {
            er.printStackTrace();
        }
        return new ExitItem(this.parent);
    }
}
