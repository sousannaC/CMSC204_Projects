//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class UnmatchedException extends Exception {
	private static final long serialVersionUID = 1L;

    public UnmatchedException(String message) {
        super(message);
    }

    public UnmatchedException() {
        super("Passwords do not match");
    }
}