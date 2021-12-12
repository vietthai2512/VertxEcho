package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OffloadVerticle extends AbstractVerticle {
  private final Logger LOGGER = LoggerFactory.getLogger(OffloadVerticle.class);

  @Override
  public void start() {
    vertx.setPeriodic(5000, id -> {
      LOGGER.info("tick");
      vertx.executeBlocking(this::blockingCode, this::resultHandler);
    });
  }

  private void blockingCode(Promise<String> promise) {
    LOGGER.info("Blocking code running");

    try {
      Thread.sleep(4000);
      LOGGER.info("Done");
      promise.complete("Ok");
    } catch (InterruptedException e) {
      promise.fail(e);
    }
  }

  private void resultHandler(AsyncResult<String> asyncResult) {
    if (asyncResult.succeeded()) {
      LOGGER.info("Blocking code result: {}", asyncResult.result());
    } else {
      LOGGER.info("Error: ", asyncResult.cause());
    }
  }
}
