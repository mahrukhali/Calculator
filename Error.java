/**
 * @author Mahrukh Ali
 *@version 1.0October 5,2022
 */

class Error {
    int lineNumber, position;
    String message;

    Error(String message, int lineNumber, int position) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.position = position;
    }

}
