package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PromiseVerticle extends AbstractVerticle {
  private final Logger LOGGER = LoggerFactory.getLogger(PromiseVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) {
    vertx.createHttpServer()
      .requestHandler(req -> req.response().end("Ok"))
      .listen(3000, httpServerAsyncResult -> {
        if (httpServerAsyncResult.succeeded()) {
          startPromise.complete();
          LOGGER.info("Listening on port 3000");
        } else {
          startPromise.fail(httpServerAsyncResult.cause());
          LOGGER.error("Cannot use this port, error: " + httpServerAsyncResult.cause());
        }
      });
  }
}
