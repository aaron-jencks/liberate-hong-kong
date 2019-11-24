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
    private static String employeeError = new String();
    private Employee employee;

    public FireMenu(ITermController parent)
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

        String s = "";
        if(employeeId.isBlank()){
            s = "Please enter the ID of the employee to be fired: ";
        } else if(confirm.isBlank()){
            s = "Are you sure you want to fire " + employee.getFirstName() + " ?";
        }else if(! employeeError.isEmpty()){
            employeeError = new String();
            employeeId = new String();
        }

        prompt = new_prompt + s;

        return result;
    }

    @Override
    public IMenuItem prompt() {
        if(EmployeeController.getInstance().auth().getPosition() != Position.HR){
            //only HR can hire
            return new ExitItem(this.parent);
        }
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        while (confirm.toUpperCase().indexOf('Y') < 0) {
            try {
                get_display_string();
                employeeId = UIUtil.get_input(sc, employeeId, prompt, (String x) -> {
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            employee = EmployeeController.getInstance().getEmployee(employeeId);
            if (employee == null) {
                get_display_string();
                try {
                    employeeId = new String();
                    employeeError = UIUtil.get_input(sc, employeeError, prompt + 
                    "Employee not found. Try again", (String s) -> {
                                return true;
                            });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            get_display_string();
            try {
                confirm = UIUtil.get_input(sc, confirm, confirm, (String s) -> {
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            EmployeeController.getInstance().deleteEmployee(employee);
            done = UIUtil.get_input(sc, done,
                    prompt + "Employee  has been fired. Press enter to continue.", (String s) -> {
                        return true;
                    });
            return new ExitItem(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ExitItem(this.parent);
    }
}
