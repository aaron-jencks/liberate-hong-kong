package company.exceptions;

import java.util.UUID;

public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public UUID employee_id = null;
    public String username = new String();

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, UUID employee_id)
    {
        super(message);
        this.employee_id = employee_id;
    }

    public EmployeeNotFoundException(String message, String username) {
        super(message);
        this.username = username;
    }
}