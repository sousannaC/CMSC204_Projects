//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

public class InvalidSequenceException extends Exception {
	private static final long serialVersionUID = 1L;


    public InvalidSequenceException(String message) {
        super("The password cannot contain more than two of the same character in sequence.");
    }

}