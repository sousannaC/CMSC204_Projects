//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class NoLowerAlphaException extends Exception {
	private static final long serialVersionUID = 1L;

    public NoLowerAlphaException(String message) {
        super(message);
    }

    public NoLowerAlphaException() {
        super("The password must contain at least one lowercase alphabetic character.");
    }
}
	