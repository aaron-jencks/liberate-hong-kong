package company.Controller.Interface;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Employee;
import company.Entity.Person;
import company.Entity.Enum.Position;
import company.exceptions.EmployeeNotFoundException;

public interface IEmployeeController {
    

    public Employee getEmployee(UUID id);
    public ArrayList<Employee> getAll();

    public void updateEmployee(Employee e);
    public void deleteEmployee(UUID id);
    public void deleteEmployee(Employee employee);
    
    public Employee createEmployee(Position position, String question, String answer, String username, String password, Person person);
    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last, UUID id);

    // public UUID fireEmployee(UUID employee_id) throws EmployeeNotFoundException;
    // public UUID modifyEmployeePassword(UUID employee_id, String password) throws EmployeeNotFoundException;
}
