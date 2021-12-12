package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyVerticle extends AbstractVerticle {
  private final Logger LOGGER = LoggerFactory.getLogger(EmptyVerticle.class);

  @Override
  public void start() {
    LOGGER.info("Start");
  }

  @Override
  public void stop() {
    LOGGER.info("Stop");
  }
}
