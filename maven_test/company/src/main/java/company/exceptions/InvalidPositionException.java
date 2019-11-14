package company.exceptions;

public class InvalidPositionException extends Exception {
    private static final long serialVersionUID = 1L;
    private String position;

    public InvalidPositionException(String position)
    {
        super("Unexpected position value: \"" + position + "\"");
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

}