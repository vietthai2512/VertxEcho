package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {
  private final Logger LOGGER  = LoggerFactory.getLogger(HelloVerticle.class);
  private long counter = 1;

  @Override
  public void start() {
    vertx.setPeriodic(5000, id -> LOGGER.info("tick"));

    vertx.createHttpServer()
      .requestHandler(req -> {
       LOGGER.info("Request #{} from {}", counter++, req.remoteAddress().host());
       req.response().end("Hello!");
      }).listen(8080);

    LOGGER.info("Open http://localhost:8080");
  }
}
