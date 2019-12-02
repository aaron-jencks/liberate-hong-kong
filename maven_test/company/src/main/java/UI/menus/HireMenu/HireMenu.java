package UI.menus.HireMenu;

import java.util.Scanner;

import UI.AMenu;
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

    public HireMenu(ITermController parent)
    {
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
        if(firstName.isBlank()){
            s = "First Name: ";
        } else if(lastName.isBlank()){
            s = "Last Name: ";
        }else if(position.isBlank()){
            s = "Position: ";
        }else if(confirm.isBlank()){
            s = "Confirm hiring?";
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
                firstName = UIUtil.get_input(sc, firstName, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                lastName = UIUtil.get_input(sc, lastName, prompt, (String s) -> {
                    return true;
                });
                get_display_string();
                position = UIUtil.get_input(sc, position, prompt, (String s) -> {
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
                confirm = UIUtil.get_input(sc, confirm, prompt, (String s) -> {
                    return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        get_display_string();

        Employee e = EmployeeController.getInstance()
            .createEmployee(Position.valueOf(position.toUpperCase()), firstName, lastName);

        try {
            done = UIUtil.get_input(sc, done, prompt + " Employee Hired. Employee id = " + e.getId().toString()
                    + "\n Press any key to return to the account menu.", (String s) -> {
                        return true;
                    });
        } catch (Exception er) {
            er.printStackTrace();
        }
        return new ExitItem(this.parent);
    }
}
