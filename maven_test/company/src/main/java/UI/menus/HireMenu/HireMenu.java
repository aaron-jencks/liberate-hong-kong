package UI.menus.HireMenu;

import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.EmployeeController;
import company.exceptions.InvalidPositionException;

public class HireMenu extends AMenu {
    private EmployeeController controller;

    public HireMenu(ITermController parent, EmployeeController controller)
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

        String first_name = "";
        String last_name = "";
        String position = "";

        while(true) {
            String padding = get_display_string();

            while (confirm.toUpperCase().indexOf('Y') < 0) {
                try {
                    first_name = UIUtil.get_input(sc, first_name, padding + "First Name: ", (String s) -> {
                        return true;
                    });
                    last_name = UIUtil.get_input(sc, last_name, padding + "Last Name: ", (String s) -> {
                        return true;
                    });
                    position = UIUtil.get_input(sc, position, padding + "Position: ", (String s) -> {
                        return true;
                    });
                    confirm = UIUtil.get_input(sc, confirm, padding + "Confirm hiring this person? ", (String s) -> {
                        return true;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                UUID id = controller.createEmployee(first_name, last_name, position);
                confirm = UIUtil.get_input(sc, confirm, padding + "Employee " + id + " has been created. Press enter to continue.", (String s) -> {
                    return true;
                });
                return new ExitItem(parent);
            }
            catch(InvalidPositionException e) {
                try {
                    UIUtil.get_input(sc, confirm, padding + e.getMessage() + " Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                invalidate();
//                return null;
                return new ExitItem(parent);
            }
            catch (Exception e) {
                try {
                    UIUtil.get_input(sc, confirm, padding + e.getMessage() + " Press enter to continue.", (String s) -> true);
                }
                catch (Exception e1) { e1.printStackTrace(); }

                return new ExitItem(parent);
            }
        }



    }
}
