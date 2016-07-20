package com.spark.tutorial

import SparkCommon._
import org.apache.spark.sql.functions._

object App extends App {
  val paths = for {
    filename <- args
  } yield filename
  val events = scSQL.read.json(paths:_*)

  val employees = List("greatfirebot", "KenanSulayman", "manuelrp07")
  val bcEmployees = sc.broadcast(employees)
  val isEmployee = (username: String) => bcEmployees.value.contains(username)
  val isEmployeeFunc = scSQL.udf.register("isEmployee", isEmployee)

  import scSQL.implicits._
  val pushEvents = events.filter("type = 'PushEvent'").filter(isEmployeeFunc($"actor.login"))
  println(s"Total number of push events from Grump Group: ${pushEvents.count()}")

  //println("Event schema:")
  //pushEvents.printSchema()

  val topPushers = pushEvents.groupBy("actor.login").count().sort(desc("count"))
  val numToShow = 10
  println(s"Top $numToShow commiters:")
  topPushers.show(numToShow)
}