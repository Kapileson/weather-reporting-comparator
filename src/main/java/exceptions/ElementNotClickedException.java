package exceptions;

public class ElementNotClickedException extends RuntimeException{

    public ElementNotClickedException(String message){
        super(message);
    }

}
