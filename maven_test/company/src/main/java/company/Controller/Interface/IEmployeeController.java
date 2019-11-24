package company.Controller.Interface;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Employee;
import company.Entity.Person;
import company.Entity.Enum.Position;

public interface IEmployeeController {
    

    public Employee getEmployee(UUID id);
    public ArrayList<Employee> getAll();

    public void updateEmployee(Employee e);
    public void deleteEmployee(UUID id);
    public void deleteEmployee(Employee employee);
    
    public Employee createEmployee(Position position, String question, String answer, String username, String password, Person person);
    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last, UUID id);
}
