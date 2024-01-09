package exception;

public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }

    public InvalidInputException() {
        super("Invalid input.");
    }
}
