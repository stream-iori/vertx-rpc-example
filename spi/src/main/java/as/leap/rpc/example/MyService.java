package as.leap.rpc.example;

import as.leap.vertx.rpc.RequestProp;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import rx.Observable;

import java.util.concurrent.CompletableFuture;

/**
 * Created by stream.
 */
public interface MyService {

  @RequestProp(timeout = 5000, retry = 2)
  void serviceOne(User user, Handler<AsyncResult<User>> handler);

  Observable<User> serviceTwo(User user);

  CompletableFuture<User> serviceThree(User user);

  void serviceException(User user, Handler<AsyncResult<User>> handler);

}
