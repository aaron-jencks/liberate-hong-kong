package company.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Person;
import company.Entity.SQLEmployee;
import company.Entity.SQLPerson;
import company.Entity.Enum.Position;


public class EmployeeController extends SQLController {

    private static EmployeeController controllerInstance = null;
    private static String TABLE_NAME = "EMPLOYEE";
    
    protected static String EMP_QUESTION_CONST = "question";
    protected static String EMP_ANSWER_CONST = "answer";
    protected static String EMP_USERNAME_CONST = "username";
    protected static String EMP_PASSWORD_CONST = "password";



    /**
     * Create the instance of the singleton
     * @return EmployeeController
     */
    public static EmployeeController getInstance(){
        if(controllerInstance == null){
            createTable();
            controllerInstance = new EmployeeController();
        }
        return controllerInstance;
    }

    /**
     * Create an employee from a result set
     * @param employeeResult
     * @return SQLEmployee
     */
    private SQLEmployee createEmployee(ResultSet employeeResult){
        String username = null, password = null, question = null, answer = null;
        UUID id = null;
        try {
            username = employeeResult.getString(EMP_USERNAME_CONST);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.username (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            password = employeeResult.getString(EMP_PASSWORD_CONST);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.password (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            question = employeeResult.getString(EMP_QUESTION_CONST);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.question (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            answer = employeeResult.getString(EMP_ANSWER_CONST);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.answer (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            id = UUID.fromString(employeeResult.getString("ID"));
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee. (EmployeeController.createEmployee)");
            debugError(er);
        }
        SQLPerson p = PersonController.getInstance().getPerson(id);
        return new SQLEmployee(id, Position.TELLER, username, password, question, answer, p);
    }

    /**
     * Get an employee based on their id
     * @param id
     * @return SQLEmployee
     */
    public SQLEmployee getEmployee(UUID id){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + 
                    " WHERE ID = " + sqlPrepare(id);
        SQLEmployee e = null;
        if(SQLController.debug){
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
                e = createEmployee(resultSet);
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
        return e;
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
    public SQLEmployee createEmployee(String question, String answer, String username, String password, SQLPerson person){
        if(person == null){
            throw new NullPointerException("Passed person was null : (EmployeeController.createEmployee)");
        }
        return EmployeeController.getInstance().createEmployee(question, answer, username, password, person.getFirstName(), person.getLastName(), person.getId());
    }

    /**
     * Create an employee based on the passed parameters
     * @param question
     * @param answer
     * @param username
     * @param password
     * @return SQLEmployee
     */
    public SQLEmployee createEmployee(String question, String answer, String username, String password, String first, String last, UUID id){
        PersonController pc = PersonController.getInstance();
        SQLPerson p = pc.createPerson(first, last);
        String sql = "INSERT INTO " + TABLE_NAME + 
                    " ( ID , " + 
                    EMP_QUESTION_CONST + " , " + 
                    EMP_ANSWER_CONST + " , " + 
                    EMP_USERNAME_CONST + " , " + 
                    EMP_PASSWORD_CONST +
                    " ) VALUES ( " + 
                    sqlPrepare(id) + " , " + 
                    sqlPrepare(question) + " , " + 
                    sqlPrepare(answer) + " , " + 
                    sqlPrepare(username) + " , " + 
                    sqlPrepare(password) +
                    " ) ";
        executeUpdate(sql);
        return new SQLEmployee(id, Position.TELLER, username, password, question, answer, first, last);
    }

    /**
     * Get all the employees
     * @return ArrayList<SQLEmployee>
     */
    public ArrayList<SQLEmployee> getAll(){
        ArrayList<SQLEmployee> allEmployees = new ArrayList<>();
        String sqlQuery = " SELECT * FROM " + TABLE_NAME;
        if(SQLController.debug){
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
    private static void createTable(){
        String[] params = {
            EMP_USERNAME_CONST + " VARCHAR(255)",
            EMP_PASSWORD_CONST + " VARCHAR(255)",
            EMP_QUESTION_CONST + " VARCHAR(255)",
            EMP_ANSWER_CONST + " VARCHAR(255)",
        };
        create(TABLE_NAME, params);
    }

    /**
     * Update all employee attributes
     */
    public void updateEmployee(SQLEmployee e){
        String[] params = {
            EMP_USERNAME_CONST + " = " + sqlPrepare(e.getUsername()),
            EMP_PASSWORD_CONST + " = " + sqlPrepare(e.getPassword()),
            EMP_ANSWER_CONST + " = " + sqlPrepare(e.getAnswer()),
            EMP_QUESTION_CONST + " = " + sqlPrepare(e.getQuestion())
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
    public void deleteEmployee(SQLEmployee employee){
        if(employee == null){
            return;
        }
        deleteEmployee(employee.getId());
    }

    /**
     * Drop the table
     */
    public void dropTable(){
        drop(TABLE_NAME);
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        truncate(TABLE_NAME);
    }









    /*
     * @Override public UUID createEmployee(String first_name, String last_name,
     * String position) throws Exception { Person p = new Person(first_name,
     * last_name); IEmployee employee = vault.getEmployee(p); if (employee != null)
     * throw new Exception("Person is already an employee");
     * 
     * UUID employee_id; switch (position) { case "Teller": employee_id =
     * vault.createTeller(p); break; case "Loan Officer": employee_id =
     * vault.createLoanOfficer(p); break; case "Manager": employee_id =
     * vault.createManager(p); break; case "HR Manager": employee_id =
     * vault.createHRManager(p); break; case "Owner": employee_id =
     * vault.createOwner(p); break; default: throw new
     * IllegalStateException("Unexpected value: " + position); }
     * 
     * return employee_id; }
     * 
     * public UUID findEmployee(String username) throws EmployeeNotFoundException {
     * IEmployee employee = vault.getEmployee(username); if(employee == null) throw
     * new EmployeeNotFoundException("Employee not found", username); return
     * employee.getObjectId(); }
     * 
     * public IEmployee findEmployee(UUID user_id) throws EmployeeNotFoundException
     * { IEmployee employee = vault.getEmployee(user_id); if(employee == null) throw
     * new EmployeeNotFoundException("Employee not found", user_id); return
     * employee; }
     * 
     * public UUID fireEmployee(UUID employee_id) throws EmployeeNotFoundException {
     * IEmployee employee = vault.getEmployee(employee_id); if (employee == null)
     * throw new EmployeeNotFoundException("Employee not found", employee_id);
     * return vault.fireEmployee(employee_id); }
     * 
     * /** Promotes an existing employee to a new position if they exist and are not
     * already in the position.
     * 
     * @param employee_id UUID of the existing employee
     * 
     * @param position the new position the employee will have
     * 
     * @return UUID of the promoted employee
     * 
     * @throws EmployeeNotFoundException if the employee id cannot be found in the
     * vault
     * 
     * @throws Exception if the given position is not valid
     */
    /*
     * public UUID promoteEmployee(UUID employee_id, String position) throws
     * EmployeeNotFoundException, Exception { IEmployee employee =
     * vault.getEmployee(employee_id); if (employee == null) throw new
     * EmployeeNotFoundException("Employee not found", employee_id);
     * 
     * switch (position) { case "Teller": if
     * (Teller.class.isAssignableFrom(employee.getClass())) throw new
     * Exception("Employee is already a Teller."); break; case "Loan Officer": if
     * (LoanOfficer.class.isAssignableFrom(employee.getClass())) throw new
     * Exception("Employee is already a Loan Officer."); break; case "Manager": if
     * (Manager.class.isAssignableFrom(employee.getClass())) throw new
     * Exception("Employee is already a Manager."); break; case "HR Manager": if
     * (HRManager.class.isAssignableFrom(employee.getClass())) throw new
     * Exception("Employee is already a HR Manager."); break; case "Owner": if
     * (Owner.class.isAssignableFrom(employee.getClass())) throw new
     * Exception("Employee is already an Owner.\n" + employee.getClass()); break;
     * default: throw new IllegalStateException("Unexpected value: \"" + position +
     * "\""); }
     * 
     * return vault.promoteEmployee(employee_id, position); }
     * 
     * public UUID modifyEmployeePassword(UUID employee_id, String password) throws
     * EmployeeNotFoundException { IEmployee employee =
     * vault.getEmployee(employee_id); if(employee == null) throw new
     * EmployeeNotFoundException("Employee not found", employee_id);
     * employee.setEmployeePassword(password); return employee_id; }
     */
}
