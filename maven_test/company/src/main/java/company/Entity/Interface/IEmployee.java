package company.Entity.Interface;

public interface IEmployee extends IPerson {
    public long getEmployeeID();
    public IEmployee setEmployeeID(long employee_id);
    public String getEmployeeUsername();
    public IEmployee setEmployeeUsername(String username);
    public String getEmployeePassword();
    public IEmployee setEmployeePassword(String password);
    public String getEmployeeSecurityQuestion();
    public IEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion);
    public String getEmployeeSecurityAnswer();
    public IEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer);
}
