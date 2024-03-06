package aleksandra0KRBank.Exceptions;
/**
 * Exception class for the user
 * @author Aleksandra0KR
 * @version 1.0
 */
public class UserExceptions extends RuntimeException {
    public UserExceptions(String errorMessage) {
        super(errorMessage);
    }
}