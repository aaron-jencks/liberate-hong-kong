package company.Controller.Interface;

import java.util.UUID;

import company.Entity.Interface.IEmployee;
import company.exceptions.EmployeeNotFoundException;

public interface IEmployeeController {
    public UUID createEmployee(String first_name, String last_name, String position) throws Exception;
    public UUID findEmployee(String username) throws EmployeeNotFoundException;
    public IEmployee findEmployee(UUID user_id) throws EmployeeNotFoundException;
    public UUID fireEmployee(UUID employee_id) throws EmployeeNotFoundException;
    public UUID modifyEmployeePassword(UUID employee_id, String password) throws EmployeeNotFoundException;
}
