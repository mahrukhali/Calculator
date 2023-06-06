/**
 * @author Mahrukh Ali
 *
 **@uni ma4203
 *@version 1.0October 5,2022
 */

import java.io.Serial;

class StackException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public StackException(final String msg) {
        super(msg);
    }

    public StackException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
