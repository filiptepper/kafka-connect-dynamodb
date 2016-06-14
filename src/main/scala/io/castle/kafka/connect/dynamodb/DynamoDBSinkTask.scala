package io.castle.kafka.connect.dynamodb

import java.util

import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}

import awscala._
import dynamodbv2._

class DynamoDBSinkTask extends SinkTask with Logging {
  var writer: Option[DynamoDB] = None

  override def start(props: util.Map[String, String]): Unit = {
    logger.info("Starting DynamoDB sink...")

    val taskConfig = new DynamoDBSinkConfig(props)

    // this is only local
    // need to change the library so that we can query local dynamodb at any address
    writer = Some(DynamoDB.local())

//    KuduSinkConfig.config.parse(props)
//    val sinkConfig = new KuduSinkConfig(props)
//    val topics = context.assignment().asScala.map(c=>c.topic()).toList
//    val settings = KuduSettings(sinkConfig, topics, true)
//
//    //if error policy is retry set retry interval
//    if (settings.errorPolicy.equals(ErrorPolicyEnum.RETRY)) {
//      context.timeout(sinkConfig.getInt(KuduSinkConfig.ERROR_RETRY_INTERVAL).toLong)
//    }
//
//    writer = Some(KuduWriter(sinkConfig, settings))
  }

  override def put(records: util.Collection[SinkRecord]): Unit = {
    require(writer.nonEmpty, "Writer is not set!")

    val activities: Table = writer.table("activities").get
    writer.foreach(w => activities.put(1, "a" -> "b"))
  }

  override def stop(): Unit = {
    logger.info("Stopping DynamoDB sink...")

    writer.foreach(w => w.shutdown())
  }

  override def flush(map: util.Map[TopicPartition, OffsetAndMetadata]): Unit = {
    logger.info("Flushing DynamoDB sink.")
  }

  override def version(): String = getClass.getPackage.getImplementationVersion
}
