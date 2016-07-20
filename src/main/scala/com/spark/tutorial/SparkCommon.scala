package com.spark.tutorial

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

object SparkCommon {
  lazy val conf = {
    new SparkConf(loadDefaults = true)
  }

  lazy val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")
  lazy val scSQL = SQLContext.getOrCreate(sc)
}
