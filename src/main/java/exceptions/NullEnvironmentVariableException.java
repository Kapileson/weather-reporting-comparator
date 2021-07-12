package exceptions;

public class NullEnvironmentVariableException extends RuntimeException{

    public NullEnvironmentVariableException(String message){
        super(message);
    }
}
