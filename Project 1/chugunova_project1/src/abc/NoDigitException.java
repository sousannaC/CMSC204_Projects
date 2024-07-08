//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class NoDigitException extends Exception {
	private static final long serialVersionUID = 1L;

    public NoDigitException(String message) {
        super(message);
    }

    public NoDigitException() {
        super("The password must contain at least one digit.");
    }
}