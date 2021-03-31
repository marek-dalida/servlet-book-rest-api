package zpt.responses;

public class ExceptionResponse extends Response{
    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, int status) {
        super(message, status);
    }
}
