package ensta;

/**
 * OutOfBound
 */
public class IncorrectPosition extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IncorrectPosition(String s) {
        super(s);
    }
}