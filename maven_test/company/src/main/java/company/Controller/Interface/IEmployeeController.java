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
    

    /**
     * Get employee by id
     * @param id
     * @return
     */
    public Employee getEmployee(UUID id);

    /**
     * Get all employee as list
     * @return
     */
    public ArrayList<Employee> getAll();

    /**
     * Delete an employee from the DB with the id
     * @param id
     */
    public void deleteEmployee(UUID id);

    /**
     * Delete the employee from the db
     * @param employee
     */
    public void deleteEmployee(Employee employee);
    
    /**
     * Create a new Employee
     * @param position
     * @param question
     * @param answer
     * @param username
     * @param password
     * @param person
     * @return
     */
    public Employee createEmployee(Position position, String question, String answer, String username, String password, Person person);
    /**
     * Create a new Employee
     * @param position
     * @param question
     * @param answer
     * @param username
     * @param password
     * @param first
     * @param last
     * @param id
     * @return
     */
    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last, UUID id);

    /**
     * Makes sure the logged in employee has the correct position and then deletes the passed employee
     * @param employee
     */
    public void fire(Employee employee);
}
