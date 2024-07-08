//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class WeakPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

    public WeakPasswordException(String message) {
        super(message);
    }

    public WeakPasswordException() {
        super("The password is OK but weak - it contains between 6 and 9 characters.");
    }
}