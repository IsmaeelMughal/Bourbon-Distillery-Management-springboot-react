package vumc.org.springreact.exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException(String message)
    {
        super(message);
    }
}
