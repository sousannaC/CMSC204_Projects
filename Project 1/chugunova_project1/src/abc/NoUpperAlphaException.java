//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class NoUpperAlphaException extends Exception {
	private static final long serialVersionUID = 1L;

    public NoUpperAlphaException(String message) {
        super(message);
    }

    public NoUpperAlphaException() {
        super("The password must contain at least one uppercase alphabetic character.");
    }
}