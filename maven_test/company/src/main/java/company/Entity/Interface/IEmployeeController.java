package company.Entity.Interface;

import company.exceptions.EmployeeNotFoundException;

public interface IEmployeeController {
    public long createEmployee(String first_name, String last_name, String position) throws Exception;
    public long findEmployee(String username) throws EmployeeNotFoundException;
    public IEmployee findEmployee(long user_id) throws EmployeeNotFoundException;
    public long fireEmployee(long employee_id) throws EmployeeNotFoundException;
    public long modifyEmployeePassword(long employee_id, String password) throws EmployeeNotFoundException;
}
