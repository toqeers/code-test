package uk.co.ts;

public class ApplicationException extends RuntimeException{
    public ApplicationException() {
        super();
    }

    public ApplicationException(String s) {
        super(s);
    }

    public ApplicationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ApplicationException(Throwable throwable) {
        super(throwable);
    }

    protected ApplicationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
