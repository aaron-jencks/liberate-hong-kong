package company.Controller;

import java.util.UUID;

import company.Controller.Interface.IEmployeeController;
import company.Entity.*;
import company.Entity.Abstract.AVault;
import company.Entity.Interface.IEmployee;
import company.exceptions.EmployeeNotFoundException;

public class EmployeeController implements IEmployeeController {
    private AVault vault;

    public EmployeeController(AVault vault) {
        this.vault = Vault.getInstance();
    }

    @Override
    public UUID createEmployee(String first_name, String last_name, String position) throws Exception {
        Person p = new Person(first_name, last_name);
        IEmployee employee = vault.getEmployee(p);
        if (employee != null)
            throw new Exception("Person is already an employee");

        UUID employee_id;
        switch (position) {
            case "Teller":
                employee_id = vault.createTeller(p);
                break;
            case "Loan Officer":
                employee_id = vault.createLoanOfficer(p);
                break;
            case "Manager":
                employee_id = vault.createManager(p);
                break;
            case "HR Manager":
                employee_id = vault.createHRManager(p);
                break;
            case "Owner":
                employee_id = vault.createOwner(p);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }

        return employee_id;
    }

    public UUID findEmployee(String username) throws EmployeeNotFoundException {
        IEmployee employee = vault.getEmployee(username);
        if(employee == null)
            throw new EmployeeNotFoundException("Employee not found", username);
        return employee.getObjectId();
    }

    public IEmployee findEmployee(UUID user_id) throws EmployeeNotFoundException {
        IEmployee employee = vault.getEmployee(user_id);
        if(employee == null)
            throw new EmployeeNotFoundException("Employee not found", user_id);
        return employee;
    }

    public UUID fireEmployee(UUID employee_id) throws EmployeeNotFoundException {
        IEmployee employee = vault.getEmployee(employee_id);
        if (employee == null)
            throw new EmployeeNotFoundException("Employee not found", employee_id);
        return vault.fireEmployee(employee_id);
    }

    /**
     * Promotes an existing employee to a new position if they exist and are not already in the position.
     * @param employee_id UUID of the existing employee
     * @param position the new position the employee will have
     * @return UUID of the promoted employee
     * @throws EmployeeNotFoundException if the employee id cannot be found in the vault
     * @throws Exception if the given position is not valid
     */
    public UUID promoteEmployee(UUID employee_id, String position) throws EmployeeNotFoundException, Exception {
        IEmployee employee = vault.getEmployee(employee_id);
        if (employee == null)
            throw new EmployeeNotFoundException("Employee not found", employee_id);

        switch (position) {
            case "Teller":
                if (Teller.class.isAssignableFrom(employee.getClass()))
                    throw new Exception("Employee is already a Teller.");
                break;
            case "Loan Officer":
                if (LoanOfficer.class.isAssignableFrom(employee.getClass()))
                    throw new Exception("Employee is already a Loan Officer.");
                break;
            case "Manager":
                if (Manager.class.isAssignableFrom(employee.getClass()))
                    throw new Exception("Employee is already a Manager.");
                break;
            case "HR Manager":
                if (HRManager.class.isAssignableFrom(employee.getClass()))
                    throw new Exception("Employee is already a HR Manager.");
                break;
            case "Owner":
                if (Owner.class.isAssignableFrom(employee.getClass()))
                    throw new Exception("Employee is already an Owner.\n" + employee.getClass());
                break;
            default:
                throw new IllegalStateException("Unexpected value: \"" + position + "\"");
        }

        return vault.promoteEmployee(employee_id, position);
    }

    public UUID modifyEmployeePassword(UUID employee_id, String password) throws EmployeeNotFoundException
    {
        IEmployee employee = vault.getEmployee(employee_id);
        if(employee == null)
            throw new EmployeeNotFoundException("Employee not found", employee_id);
        employee.setEmployeePassword(password);
        return employee_id;
    }
}
