package First_Project.exception;

/**
 * Created by Dotin school 6 on 4/10/2016.
 */
public class ExceptionNegativeBalance extends Exception{
    public ExceptionNegativeBalance(){
        super("Warning!!! the balance is negative or zero!");
    }
}
