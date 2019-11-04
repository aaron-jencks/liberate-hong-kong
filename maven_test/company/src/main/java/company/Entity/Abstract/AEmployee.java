package company.Entity.Abstract;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{

    protected long employeeID = 0;
    protected String employeeUsername = new String();
    protected String employeePassword = new String();
    protected String employeeSecurityQuestion = new String();
    protected String employeeSecurityAnswer = new String();


    public AEmployee(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName);
        this.employeeUsername = employeeUsername;
        this.employeePassword = "";
        this.employeeID = employeeID;
    }

    public AEmployee(){
        super();
        this.employeeID = 0l;
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

    private static JSONArray add(JSONArray a1, JSONArray a2){
        JSONArray master = new JSONArray();
        for(int i = 0; i < a1.length(); i++){
            master.put(a1.getJSONObject(i));
        }
        for(int i = 0; i < a2.length(); i++){
            master.put(a2.getJSONObject(i));
        }
        return master;
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

    public long getEmployeeID()
    {
        return employeeID;
    }

    public AEmployee setEmployeeID(long employeeID)
    {
        this.employeeID = employeeID;
        return this;
    }

    public String getEmployeePassword()
    {
        return employeePassword;
    }

    public AEmployee setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
        return this;
    }

    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    public AEmployee setEmployeeUsername(String employeeUsername)
    {
        this.employeeUsername = employeeUsername;
        return this;
    }

    public String getEmployeeSecurityQuestion()
    {
        return employeeSecurityQuestion;
    }

    public AEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion)
    {
        this.employeeSecurityQuestion = employeeSecurityQuestion;
        return this;
    }

    public String getEmployeeSecurityAnswer()
    {
        return employeeSecurityAnswer;
    }

    public AEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer)
    {
        this.employeeSecurityAnswer = employeeSecurityAnswer;
        return this;
    }

}
