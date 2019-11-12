package UI.menus.FireMenu;

import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;

public class FireMenu extends AMenu {
    private EmployeeController controller;

    public FireMenu(ITermController parent, EmployeeController controller)
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
        Scanner sc = new Scanner(System.in);
        String confirm = "";

        if(!is_valid)
            display();

        String employee_id = "";

        while(true) {
            String padding = get_display_string();

            while (confirm.toUpperCase().indexOf('Y') < 0) {
                try {
                    employee_id = UIUtil.get_input(sc, employee_id, padding + "Employee ID: ", (String x) -> {
                        return true;
                    });
                    confirm = UIUtil.get_input(sc, confirm, padding + "Confirm firing this person? ", (String s) -> {
                        return true;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            try {
                UUID id = controller.fireEmployee(UUID.fromString(employee_id));
                confirm = UIUtil.get_input(sc, confirm, padding + "Employee " + id + " has been fired. Press enter to continue.", (String s) -> {
                    return true;
                });
                return new ExitItem(parent);
            }
            catch (Exception e) {
                if (e.getMessage().equals("Employee not found")) {
                    try {
                        confirm = UIUtil.get_input(sc, confirm, padding + "Employee not found. Please try again. Press enter to continue.", (String s) -> {
                            return true;
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    e.printStackTrace();
                    return new ExitItem(parent);
                }
            }
        }



    }
}
