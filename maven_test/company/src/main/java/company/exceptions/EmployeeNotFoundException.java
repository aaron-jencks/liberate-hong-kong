package company.exceptions;

public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public long employee_id = -1;
    public String username = new String();

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, long employee_id)
    {
        super(message);
        this.employee_id = employee_id;
    }

    public EmployeeNotFoundException(String message, String username) {
        super(message);
        this.username = username;
    }
}