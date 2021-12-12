package com.example;

import com.example.verticles.*;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetSocket;

public class VertxEcho {
  private static int numberOfConnections = 0;

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    //vertx.deployVerticle(new HelloVerticle());
    //vertx.deployVerticle(new PromiseVerticle());
    //vertx.deployVerticle(new DeployerVerticle());

    // Deploy Configuration verticle
    /*for (int n = 0; n < 5; n++) {
      JsonObject conf = new JsonObject().put("n", n);
      DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(conf).setInstances(n);
      vertx.deployVerticle(ConfigurationVerticle.class.getCanonicalName(), deploymentOptions);
    }*/

    // Deploy worker verticle
    //DeploymentOptions opts = new DeploymentOptions().setInstances(2).setWorker(true);
    //vertx.deployVerticle(WorkerVerticle.class.getCanonicalName(), opts);

    // Offload worker thread
    vertx.deployVerticle(new OffloadVerticle());
  }

  private static void handleNewClient(NetSocket socket) {
    numberOfConnections++;

    socket.handler(buffer -> {
      socket.write(socket.localAddress() + " said: " + buffer);
      if (buffer.toString().endsWith("/quit\n")) {
        socket.close();
      }
    });

    socket.closeHandler(v -> numberOfConnections--);
  }

  private static String howMany() {
    return "We now have " + numberOfConnections + " connections.";
  }
}
