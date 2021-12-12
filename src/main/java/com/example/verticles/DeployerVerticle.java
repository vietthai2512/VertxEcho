package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeployerVerticle extends AbstractVerticle {
  private final Logger LOGGER  = LoggerFactory.getLogger(DeployerVerticle.class);

  @Override
  public void start() {
    long delay = 1000;

    for (int i = 0; i < 50; i++) {
      vertx.setTimer(delay, id -> deploy());
      delay += 1000;
    }
  }

  private void deploy() {
    vertx.deployVerticle(new EmptyVerticle(), asyncResult -> {
      if (asyncResult.succeeded()) {
        String id = asyncResult.result();
        LOGGER.info("Successfully deployed {}", id);
        vertx.setTimer(5000, tid -> undeploy(id));
      } else {
        LOGGER.error("Error while deploying: " + asyncResult.cause());
      }
    });
  }

  private void undeploy(String id) {
    vertx.undeploy(id, asyncResult -> {
      if (asyncResult.succeeded()) {
        LOGGER.info("{} was undeployed", id);
      } else {
        LOGGER.error("{} could not be undeployed", id);
      }
    });
  }
}
