//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class LengthException extends Exception {
	private static final long serialVersionUID = 1L;

    public LengthException(String message) {
        super(message);
    }
public LengthException() {
    super("The password must be at least 6 characters long.");
	}
}