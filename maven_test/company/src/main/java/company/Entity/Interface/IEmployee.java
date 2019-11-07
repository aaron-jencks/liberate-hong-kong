package company.Entity.Interface;

import java.util.UUID;

public interface IEmployee extends IPerson {
    public String getEmployeeUsername();
    public IEmployee setEmployeeUsername(String username);
    public String getEmployeePassword();
    public IEmployee setEmployeePassword(String password);
    public String getEmployeeSecurityQuestion();
    public IEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion);
    public String getEmployeeSecurityAnswer();
    public IEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer);
}
