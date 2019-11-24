package company.Controller.Interface;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Employee;
import company.Entity.Person;
import company.Entity.Enum.Position;

public interface IEmployeeController {

    static String TABLE_NAME = "EMPLOYEE";
    static String EMP_QUESTION = "question";
    static String EMP_ANSWER = "answer";
    static String EMP_USERNAME = "username";
    static String EMP_PASSWORD = "password";
    static String EMP_POSITION = "position";
    

    public Employee getEmployee(UUID id);
    public ArrayList<Employee> getAll();

    public void updateEmployee(Employee e);
    public void deleteEmployee(UUID id);
    public void deleteEmployee(Employee employee);
    
    public Employee createEmployee(Position position, String question, String answer, String username, String password, Person person);
    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last, UUID id);
}
