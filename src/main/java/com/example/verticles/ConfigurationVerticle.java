package com.example.verticles;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationVerticle extends AbstractVerticle {
  private final Logger LOGGER = LoggerFactory.getLogger(ConfigurationVerticle.class);

  @Override
  public void start() {
    LOGGER.info("n = {}", config().getInteger("n", -1));
  }
}
