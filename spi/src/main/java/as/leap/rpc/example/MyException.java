package as.leap.rpc.example;

/**
 * Created by stream.
 */
public class MyException extends RuntimeException{

  /**
   * NOTE: There are must have a String's parameter for your custom exception.
   * @param message String
   */
  public MyException(String message) {
    super(message);
  }
}
