package io.castle.kafka.connect.dynamodb

import org.slf4j.LoggerFactory

trait Logging {
  val loggerName = this.getClass.getName
  @transient lazy val logger = LoggerFactory.getLogger(loggerName)
}
