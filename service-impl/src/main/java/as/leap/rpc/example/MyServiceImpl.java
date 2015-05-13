package as.leap.rpc.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import rx.Observable;

import java.util.concurrent.CompletableFuture;

/**
 * Created by stream.
 */
public class MyServiceImpl implements MyService {

  public void serviceOne(User user, Handler<AsyncResult<User>> handler) {
    handler.handle(Future.succeededFuture(user));
  }

  public Observable<User> serviceTwo(User user) {
    return Observable.just(user);
  }

  public CompletableFuture<User> serviceThree(User user) {
    return CompletableFuture.completedFuture(user);
  }

  @Override
  public void serviceException(User user, Handler<AsyncResult<User>> handler) {
    handler.handle(Future.failedFuture(new MyException("myException")));
  }
}
