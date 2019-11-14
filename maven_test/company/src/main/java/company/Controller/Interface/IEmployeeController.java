package company.Controller.Interface;

import java.util.UUID;

import company.Entity.Interface.IEmployee;
import company.exceptions.EmployeeNotFoundException;
import company.exceptions.InvalidPositionException;

public interface IEmployeeController {

    /**
     * Creates a new employee in the vault with the given position
     * @param first_name first name of the employee
     * @param last_name last name of the employee
     * @param position position the employee will have
     * @return UUID of the employee
     * @throws InvalidPositionException if the provided position is invalid
     * @throws Exception if the person is already an employee
     */
    public UUID createEmployee(String first_name, String last_name, String position) throws InvalidPositionException, Exception;

    /**
     * Finds an employee in the vault by username
     * @param username username of the employee
     * @return UUID of the found employee
     * @throws EmployeeNotFoundException if the employee was not found
     */
    public UUID findEmployee(String username) throws EmployeeNotFoundException;

    /**
     * Finds an employee in the vault by their employee id
     * @param user_id UUID of the employee
     * @return IEmployee for the found employee
     * @throws EmployeeNotFoundException if the employee was not found
     */
    public IEmployee findEmployee(UUID user_id) throws EmployeeNotFoundException;

    /**
     * Fires an employee
     * @param employee_id UUID of the employee to fire
     * @return UUID of the fired employee
     * @throws EmployeeNotFoundException if the employee was not found in the vault
     */
    public UUID fireEmployee(UUID employee_id) throws EmployeeNotFoundException;

    /**
     * Promotes an existing employee to a new position if they exist and are not already in the position.
     * @param employee_id UUID of the existing employee
     * @param position the new position the employee will have
     * @return UUID of the promoted employee
     * @throws EmployeeNotFoundException if the employee id cannot be found in the vault
     * @throws Exception if the given position is not valid
     */
    public UUID promoteEmployee(UUID employee_id, String position) throws EmployeeNotFoundException, Exception;

    /**
     * Modifies the password of a employee
     * @param employee_id UUID of the employee whose password will be modified
     * @param password new password for the employee
     * @return UUID of the employee
     * @throws EmployeeNotFoundException if the employee id was not found in the vault
     */
    public UUID modifyEmployeePassword(UUID employee_id, String password) throws EmployeeNotFoundException;
}
