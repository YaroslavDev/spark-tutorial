package com.spark.tutorial

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

trait SparkCommon {
  def appName: String

  lazy val conf = {
    new SparkConf(loadDefaults = true)
      .setAppName(appName)
      .setMaster("local[*]")
      .set("spark.eventLog.enabled", "true")
  }

  lazy val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")
  lazy val scSQL = SQLContext.getOrCreate(sc)
}
