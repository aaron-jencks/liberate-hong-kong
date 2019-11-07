package company.Entity.Abstract;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{
    protected String employeeUsername = new String();
    protected String employeePassword = new String();
    protected String employeeSecurityQuestion = new String();
    protected String employeeSecurityAnswer = new String();


    public AEmployee(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName);
        this.employeeUsername = employeeUsername;
        this.employeePassword = "";
    }

    public AEmployee(){
        super();
        this.employeePassword = null;
        this.employeeSecurityAnswer = null;
        this.employeeSecurityQuestion = null;
        this.employeeUsername = null;
    }

    public static String LookupSecurityQuestion(String username){
        JSONArray emps = LoadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username)) {
                return obj.getString("employeeSecurityQuestion");
            }
        }
        return null;
    }

    public static boolean CheckSecurityQuestion(String username, String answer){
        JSONArray emps = LoadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeeSecurityAnswer").equals(answer)){
                return true;
            }
        }
        return false;
    }


    public static boolean CheckPassword(String username, String password){
        JSONArray emps = LoadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeePassword").equals(password)){
                return true;
            }
        }
        return false;
    }


    public static JSONArray LoadAllEmployees(){
        JSONArray tellers = ASaveable.loadAllAsJson("Teller");
        JSONArray hrManagers = ASaveable.loadAllAsJson("HRManager");
        JSONArray loanOfficers = ASaveable.loadAllAsJson("LoanOfficer");
        JSONArray owner = ASaveable.loadAllAsJson("Owner");
        JSONArray managers = ASaveable.loadAllAsJson("Manager");
        JSONArray master0 = add(tellers, hrManagers);
        JSONArray master1 = add(loanOfficers, owner);
        JSONArray master2 = add(master0, managers);
        return add(master2, master1);
    }

    /**
     * Check if an employee exists with that username
     * @param username
     * @return
     */
    public static boolean CheckEmployee(String username){
        JSONArray jsonArray = LoadAllEmployees();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            if(o.getString("employeeUsername").equals(username)){
                return true;
            }
        }
        return false;
    }

    public String getEmployeePassword()
    {
        return employeePassword;
    }

    public IEmployee setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
        return this;
    }

    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    public IEmployee setEmployeeUsername(String employeeUsername)
    {
        this.employeeUsername = employeeUsername;
        return this;
    }

    public String getEmployeeSecurityQuestion()
    {
        return employeeSecurityQuestion;
    }

    public IEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion)
    {
        this.employeeSecurityQuestion = employeeSecurityQuestion;
        return this;
    }

    public String getEmployeeSecurityAnswer()
    {
        return employeeSecurityAnswer;
    }

    public IEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer)
    {
        this.employeeSecurityAnswer = employeeSecurityAnswer;
        return this;
    }

}
