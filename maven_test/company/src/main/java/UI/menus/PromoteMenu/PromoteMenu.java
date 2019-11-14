package UI.menus.PromoteMenu;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;
import company.Entity.Abstract.AVault;
import company.Entity.Interface.IEmployee;
import company.Entity.Vault;
import company.exceptions.EmployeeNotFoundException;

import java.util.Scanner;
import java.util.UUID;

public class PromoteMenu extends AMenu {
    private EmployeeController controller;

    public PromoteMenu(ITermController parent, EmployeeController controller)
    {
        super(parent, controller);
        this.controller = controller;
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
//        TODO
        Vault vault = Vault.getInstance();

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

            UUID employee_id;
            try {
                employee_id = UUID.fromString(employee_id_str);
            }
            catch(IllegalArgumentException e) {
                try {
                    UIUtil.get_input(sc, confirm, padding + "Employee id " + employee_id_str + " is not a valid employee id. Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
                return null;
            }

            UUID tmp_id;
            try {
                tmp_id = controller.promoteEmployee(employee_id, position);
            }
            catch(EmployeeNotFoundException e) {
                try {
                    UIUtil.get_input(sc, confirm, padding + "Employee id " + employee_id_str + " is not a valid employee id. Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
//                return null;
                return new ExitItem(parent);
            }
            catch(Exception e) {
                try {
                    UIUtil.get_input(sc, confirm, padding + e.getMessage() + " Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
//                return null;
                return new ExitItem(parent);
            }

            try {
                UIUtil.get_input(sc, confirm, padding + "Employee " + tmp_id + " has been promoted. Press enter to continue.", (String s) -> true);
            }
            catch (Exception e) { e.printStackTrace(); }

            return new ExitItem(parent);
        }



    }
}
