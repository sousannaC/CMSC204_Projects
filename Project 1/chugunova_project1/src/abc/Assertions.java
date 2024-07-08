//programmer:Sousanna Chugunova
//CMSC204 
//Dr.Thai
package abc;

import org.junit.jupiter.api.function.Executable;

public class Assertions {

    public static Throwable assertThrows(Class<? extends Throwable> expectedType, Executable executable) {
        try {
            executable.execute();
            return null; // If no exception is thrown, return null
        } catch (Throwable actualException) {
            if (expectedType.isInstance(actualException)) {
                return actualException; // If the caught exception matches the expected type, return it
            } else {
                throw new AssertionError("Expected " + expectedType.getName() + " to be thrown, but " +
                        actualException.getClass().getName() + " was thrown instead", actualException);
            }
        }
    }
}