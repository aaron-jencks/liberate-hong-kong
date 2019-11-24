package company.Controller.Abstract;

import company.Controller.Interface.IEmployeeController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Controller.EmployeeController;
import company.Controller.PersonController;
import company.Controller.Abstract.AEmployeeController;
import company.Controller.Abstract.ASQLController;
import company.Entity.Employee;
import company.Entity.Person;
import company.Entity.Enum.Position;

public abstract class AEmployeeController extends ASQLController implements IEmployeeController{


    public void fire(Employee employee) {
        if(EmployeeController.getInstance().auth().getPosition() != Position.HR){
            //TODO throw error?
            return;
        }
        deleteEmployee(employee);
    }

    /**
     * Create an employee from a result set
     * @param employeeResult
     * @return Employee
     */
    private Employee createEmployee(ResultSet employeeResult){
        String username = null, password = null, question = null, answer = null, position = null;
        UUID id = null;
        try {
            username = employeeResult.getString(EMP_USERNAME);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.username (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            password = employeeResult.getString(EMP_PASSWORD);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.password (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            question = employeeResult.getString(EMP_QUESTION);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.question (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            answer = employeeResult.getString(EMP_ANSWER);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.answer (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            position = employeeResult.getString(EMP_POSITION);
        } catch (SQLException er) {
            debugError(er);
        }
        try {
            id = UUID.fromString(employeeResult.getString("ID"));
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee. (EmployeeController.createEmployee)");
            debugError(er);
        }
        Person p = PersonController.getInstance().getPerson(id);
        return new Employee(id, Position.valueOf(position), username, password, question, answer, p);
    }
    



    /**
     * Uses a single WHERE {key} = {value} for quick lookup
     * @param key
     * @param value
     * @return
     */
    private ArrayList<Employee> selectWhere(String key, String value){
        ArrayList<Employee> returnedEmployees = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + 
                    " WHERE " + key +  " = " + sqlPrepare(value);
        if(ASQLController.debug){
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                returnedEmployees.add(createEmployee(resultSet));
            }
        } catch (SQLException se) {
            debugError(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
        return returnedEmployees;
    }

    public Employee findByUsername(String username){
        ArrayList<Employee> list = selectWhere(EMP_USERNAME, username);
        if(list.size() > 0 ){
            return list.get(0);
        }
        return null;
    }

    /**
     * Get employee by id
     * @param id
     * @return
     */
    public Employee getEmployee(String id){
        return getEmployee(UUID.fromString(id));
    }

    /**
     * Get an employee based on their id
     * @param id
     * @return Employee
     */
    public Employee getEmployee(UUID id){
        ArrayList<Employee> list =  selectWhere("ID", id.toString());
        if(list.size() > 0 ){
            return list.get(0);
        }
        return null;
    }

    public Employee createEmployee(Position position, String firstname, String lastname){
        Person p = PersonController.getInstance().createPerson(firstname, lastname);
        return createEmployee(position, null, null, null, null, p);
    }    

    /**
     * Create an employee
     * @param question
     * @param answer
     * @param username
     * @param password
     * @param person
     * @return
     */
    public Employee createEmployee(Position position, String question, String answer, String username, String password, Person person){
        if(person == null){
            throw new NullPointerException("Passed person was null : (EmployeeController.createEmployee)");
        }
        return EmployeeController.getInstance().createEmployee(position, question, answer, username, password, person.getFirstName(), person.getLastName(), person.getId());
    }

    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last){
        Person p = PersonController.getInstance().createPerson(first, last);
        return createEmployee(position, question, answer, username, password, p);
    }

    /**
     * Create an employee based on the passed parameters
     * @param question
     * @param answer
     * @param username
     * @param password
     * @return Employee
     */
    public Employee createEmployee(Position position, String question, String answer, String username, String password, String first, String last, UUID id){
        PersonController pc = PersonController.getInstance();
        pc.createPerson(first, last);
        String sql = "INSERT INTO " + TABLE_NAME + 
                    " ( ID , " + 
                    implode(new String[]{
                        EMP_QUESTION,
                        EMP_ANSWER,
                        EMP_USERNAME,
                        EMP_PASSWORD,
                        EMP_POSITION,
                    }) + 
                    " ) VALUES ( " + 
                    implode(new String[]{
                        sqlPrepare(id),
                        sqlPrepare(question),
                        sqlPrepare(answer),
                        sqlPrepare(username),
                        sqlPrepare(password),
                        sqlPrepare(position.toString())
                    }) + " ) ";
        executeUpdate(sql);
        return new Employee(id, position, username, password, question, answer, first, last);
    }

    /**
     * Get all the employees
     * @return ArrayList<Employee>
     */
    public ArrayList<Employee> getAll(){
        ArrayList<Employee> allEmployees = new ArrayList<>();
        String sqlQuery = " SELECT * FROM " + TABLE_NAME;
        if(ASQLController.debug){
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                allEmployees.add(createEmployee(resultSet));
            }
        } catch (SQLException se) {
            debugError(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allEmployees;
    }

    /**
     * Create the employee table
     */
    protected static void createTable(){
        String[] params = {
            EMP_USERNAME + " VARCHAR(255)",
            EMP_PASSWORD + " VARCHAR(255)",
            EMP_QUESTION + " VARCHAR(255)",
            EMP_ANSWER + " VARCHAR(255)",
            EMP_POSITION + " VARCHAR(255)"
        };
        create(TABLE_NAME, params);
    }

    /**
     * Update all employee attributes
     */
    public void updateEmployee(Employee e){
        String[] params = {
            EMP_USERNAME + " = " + sqlPrepare(e.getUsername()),
            EMP_PASSWORD + " = " + sqlPrepare(e.getPassword()),
            EMP_ANSWER + " = " + sqlPrepare(e.getAnswer()),
            EMP_QUESTION + " = " + sqlPrepare(e.getQuestion()),
            EMP_POSITION + " = " + sqlPrepare(e.getPosition().toString()),
        };
        update(TABLE_NAME, e.getId(), params);
        PersonController.getInstance().updatePerson(e);
    }

    /**
     * Delete an employee
     * @param id
     */
    public void deleteEmployee(UUID id){
        delete(TABLE_NAME, id);
    }

    /**
     * Delete an employee
     * @param employee
     */
    public void deleteEmployee(Employee employee){
        if(employee == null){
            return;
        }
        deleteEmployee(employee.getId());
    }

    /**
     * Drop the table
     */
    protected void dropTable(){
        drop(TABLE_NAME);
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        truncate(TABLE_NAME);
    }
}