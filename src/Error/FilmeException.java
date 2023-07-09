package Error;

public class FilmeException extends RuntimeException {


    public FilmeException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
