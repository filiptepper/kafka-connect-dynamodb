package io.castle.kafka.connect.dynamodb

import java.util

import org.apache.kafka.common.config.{AbstractConfig, ConfigDef}
import org.apache.kafka.common.config.ConfigDef.{Importance, Type}

object DynamoDBSinkConfig {
  val DYNAMODB_REGION = "dynamodb.region"
  val DYNAMODB_REGION_DOC = "DynamoDB region."

  val DYNAMODB_AWS_KEY = "dynamodb.aws_key"
  val DYNAMODB_AWS_KEY_DOC = "AWS credentials key."

  val DYNAMODB_AWS_SECRET = "dynamodb.aws_secret"
  val DYNAMODB_AWS_SECRET_DOC = "AWS credentials secret."

  val config: ConfigDef = new ConfigDef()
    .define(DYNAMODB_REGION, Type.STRING, Importance.HIGH, DYNAMODB_REGION_DOC)
    .define(DYNAMODB_AWS_KEY, Type.STRING, Importance.HIGH, DYNAMODB_AWS_KEY_DOC)
    .define(DYNAMODB_AWS_SECRET, Type.STRING, Importance.HIGH, DYNAMODB_AWS_SECRET_DOC)
}

class DynamoDBSinkConfig(props: util.Map[String, String])
  extends AbstractConfig(DynamoDBSinkConfig.config, props) {
}