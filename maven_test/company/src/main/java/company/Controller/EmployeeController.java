package company.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.mysql.cj.protocol.Resultset;

import company.Entity.SQLEmployee;


public class EmployeeController extends SQLController {

    private static EmployeeController controllerInstance = null;

    public static String EMP_ID_CONST = "ID";
    public static String EMP_TABLE_NAME = "EMPLOYEE";
    public static String EMP_QUESTION_CONST = "question";
    public static String EMP_ANSWER_CONST = "answer";
    public static String EMP_USERNAME_CONST = "username";
    public static String EMP_PASSWORD_CONST = "password";


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
        String username, password, question, answer = null;
        UUID id = null;
        SQLEmployee e = new SQLEmployee();
        try {
            username = employeeResult.getString(EMP_USERNAME_CONST);
            e.setUsername(username);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.username (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            password = employeeResult.getString(EMP_PASSWORD_CONST);
            e.setPassword(password);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.password (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            question = employeeResult.getString(EMP_QUESTION_CONST);
            e.setQuestion(question);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.question (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            answer = employeeResult.getString(EMP_ANSWER_CONST);
            e.setAnswer(answer);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee.answer (EmployeeController.createEmployee)");
            debugError(er);
        }
        try {
            id = UUID.fromString(employeeResult.getString(EMP_ID_CONST));
            e.setId(id);
        } catch (SQLException er) {
            System.err.println("Failed to retrieve employee. (EmployeeController.createEmployee)");
            debugError(er);
        }
        return e;
    }

    /**
     * Get an employee based on their id
     * @param id
     * @return SQLEmployee
     */
    public SQLEmployee getEmployee(UUID id){
        String sqlQuery = "SELECT * FROM " + EMP_TABLE_NAME + 
                    " WHERE " + EMP_ID_CONST + " = " + sqlPrepare(id);
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
     * Create an employee based on the passed parameters
     * @param question
     * @param answer
     * @param username
     * @param password
     * @return SQLEmployee
     */
    public SQLEmployee createEmployee(String question, String answer, String username, String password){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + EMP_TABLE_NAME + 
                    " ( " + 
                    EMP_ID_CONST + " , " + 
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
        return getEmployee(id);
    }

    /**
     * Get all the employees
     * @return ArrayList<SQLEmployee>
     */
    public ArrayList<SQLEmployee> getAll(){
        ArrayList<SQLEmployee> allEmployees = new ArrayList<>();
        String sqlQuery = " SELECT * FROM " + EMP_TABLE_NAME;
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
        String sql = "CREATE TABLE IF NOT EXISTS " + EMP_TABLE_NAME + 
                    " ( " + EMP_ID_CONST + " VARCHAR(255) not NULL, " + 
                    EMP_USERNAME_CONST + " VARCHAR(255), " +
                    EMP_PASSWORD_CONST + " VARCHAR(255), " + 
                    EMP_QUESTION_CONST + " VARCHAR(255), " + 
                    EMP_ANSWER_CONST + " VARCHAR(255), " + 
                    " PRIMARY KEY ( " + EMP_ID_CONST + " ))";
        executeUpdate(sql);
    }

    /**
     * Delete an employee
     * @param id
     */
    public void deleteEmployee(UUID id){
        String sql = "DELETE FROM " + EMP_TABLE_NAME + 
                    " WHERE " + EMP_ID_CONST +
                    " = " + sqlPrepare(id);
        executeUpdate(sql);
    }


    /**
     * Drop the table
     */
    public static void dropTable(){
        String sql = "DROP TABLE " + EMP_TABLE_NAME;
        executeUpdate(sql);
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        String sql = "TRUNCATE TABLE " + EMP_TABLE_NAME;
        executeUpdate(sql);
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
