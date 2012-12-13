package com.thoughtworks.homework.salestaxes;

/**
 * Cash register exception class.
 *
 * @author iamlusuo@gmail.com
 */
public class CashRegisterException extends RuntimeException {

    private static final long serialVersionUID = 2556157906589757047L;
    private ErrorCode errorCode = ErrorCode.NULL;

    public CashRegisterException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CashRegisterException() {
        super();
    }

    public CashRegisterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CashRegisterException(final String message) {
        super(message);
    }

    public CashRegisterException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CashRegisterException(final Throwable cause) {
        super(cause);
    }

    public CashRegisterException(final Throwable cause, final ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public enum ErrorCode {
        NULL, ERROR_PARAMETER;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append(": ");
        sb.append("[").append(getErrorCode().toString()).append("]");
        if (getLocalizedMessage() != null) {
            sb.append(" ").append(getLocalizedMessage());
        }
        return sb.toString();
    }
}
