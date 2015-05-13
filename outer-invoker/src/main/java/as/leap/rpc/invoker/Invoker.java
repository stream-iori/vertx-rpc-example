package as.leap.rpc.invoker;

import as.leap.rpc.example.MyService;
import as.leap.rpc.example.MyServiceImpl;
import as.leap.rpc.example.User;
import as.leap.vertx.rpc.impl.RPCClientOptions;
import as.leap.vertx.rpc.impl.RPCServerOptions;
import as.leap.vertx.rpc.impl.VertxRPCClient;
import as.leap.vertx.rpc.impl.VertxRPCServer;
import io.vertx.core.Vertx;

/**
 * Created by stream.
 */
public class Invoker {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    String address = "serviceAddress";

    //server must be in another module, and communicate with eventbus in cluster. this just for example.
    //start server
    RPCServerOptions rpcServerOptions = new RPCServerOptions(vertx).setBusAddress(address).addService(new MyServiceImpl());
    new VertxRPCServer(rpcServerOptions);


    //start client
    RPCClientOptions<MyService> rpcClientOptions = new RPCClientOptions<MyService>(vertx).setBusAddress(address).setServiceClass(MyService.class);
    MyService myService = new VertxRPCClient<>(rpcClientOptions).bindService();

    User user = new User();
    user.setId(1);
    user.setName("name");

    //rpc invoke
    myService.serviceOne(user, event -> {
      System.out.println(event.result().getId());
      System.out.println(event.result().getName());
    });

    myService.serviceTwo(user).subscribe(anotherUser -> {
      System.out.println(anotherUser.getId());
      System.out.println(anotherUser.getName());
    }, Throwable::printStackTrace);

    myService.serviceThree(user).whenComplete((anotherUser, ex) -> {
      System.out.println(anotherUser.getId());
      System.out.println(anotherUser.getName());
    });

  }
}
