package easycache;

public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 6550251409497115934L;

    public CacheException() {
        super();
    }

    public CacheException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CacheException(final String message) {
        super(message);
    }

    public CacheException(final Throwable cause) {
        super(cause);
    }
}
