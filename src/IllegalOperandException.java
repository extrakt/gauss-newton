/**
 * Illegal Operand Exception Class
 *
 * @author Leia Glezer
 * @version 1.2
 */

public class IllegalOperandException extends Exception {

    /**
     * Empty Constructor
     */

    public IllegalOperandException() {
    }

    /**
     * Calls the message from the super class
     * @param String message
     */

    public IllegalOperandException(String message) {
        super(message);
    }
}