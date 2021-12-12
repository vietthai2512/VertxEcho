package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticle extends AbstractVerticle {
  private final Logger LOGGER = LoggerFactory.getLogger(WorkerVerticle.class);

  @Override
  public void start() {
    vertx.setPeriodic(10_000, id -> {
      try {
        LOGGER.info("I am sleeping");
        Thread.sleep(8000);
        LOGGER.info("Wake up!");
      } catch (InterruptedException e) {
        LOGGER.error("Error: ", e);
      }
    });
  }
}
