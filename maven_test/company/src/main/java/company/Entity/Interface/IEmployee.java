package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IEmployee extends IPerson {

    /**
     * Grab all the different employee types and return them as json Objects in a json Array
     * @return
     */
    public static JSONArray loadAllEmployees(){
        JSONArray tellers = ISaveable.loadAllAsJson("Teller");
        JSONArray hrManagers = ISaveable.loadAllAsJson("HRManager");
        JSONArray loanOfficers = ISaveable.loadAllAsJson("LoanOfficer");
        JSONArray owner = ISaveable.loadAllAsJson("Owner");
        JSONArray managers = ISaveable.loadAllAsJson("Manager");
        JSONArray master0 = ISaveable.add(tellers, hrManagers);
        JSONArray master1 = ISaveable.add(loanOfficers, owner);
        JSONArray master2 = ISaveable.add(master0, managers);
        return ISaveable.add(master2, master1);
    }

    /**
     * Retrieve the security question for the username
     * @param username
     * @return
     */
    public static String lookupSecurityQuestion(String username){
        JSONArray emps = loadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username)) {
                return obj.getString("employeeSecurityQuestion");
            }
        }
        return null;
    }

    /**
     * Check if the security answer for the username matches the answer
     * @param username
     * @param answer
     * @return
     */
    public static boolean checkSecurityQuestion(String username, String answer){
        JSONArray emps = loadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeeSecurityAnswer").equals(answer)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check to see if the given username and password are valid
     * @param username
     * @param password
     * @return
     */
    public static boolean checkPassword(String username, String password){
        JSONArray emps = loadAllEmployees();
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeePassword").equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if an employee exists with that username
     * @param username
     * @return
     */
    public static boolean checkEmployee(String username){
        JSONArray jsonArray = loadAllEmployees();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            if(o.getString("employeeUsername").equals(username)){
                return true;
            }
        }
        return false;
    }



    public String getEmployeeUsername();
    public IEmployee setEmployeeUsername(String username);
    public String getEmployeePassword();
    public IEmployee setEmployeePassword(String password);
    public String getEmployeeSecurityQuestion();
    public IEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion);
    public String getEmployeeSecurityAnswer();
    public IEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer);
}
