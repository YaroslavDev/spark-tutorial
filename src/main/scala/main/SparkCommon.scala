package main

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

object SparkCommon {
  lazy val conf = {
    new SparkConf()
  }

  lazy val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")
  lazy val scSQL = SQLContext.getOrCreate(sc)
}
