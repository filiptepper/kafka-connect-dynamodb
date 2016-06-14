package io.castle.kafka.connect.dynamodb

import java.util

import org.apache.kafka.connect.sink.SinkConnector
import scala.collection.JavaConverters._

class DynamoDBSinkConnector extends SinkConnector with Logging {
  override def taskClass(): Class[_ <: Task] = classOf[TwitterSinkTask]
}
