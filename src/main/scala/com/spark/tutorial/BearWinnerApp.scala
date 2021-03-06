package com.spark.tutorial

object BearWinnerApp extends App with SparkCommon {
  override val appName = "BearWinnerApp"

  val tranFile = sc.textFile(args(0))
  val tranData = tranFile.map(_.split("#"))
  val transByCust = tranData.map(t => (t(2).toInt, t))
  val bearWinner = transByCust.countByKey().toSeq.maxBy(_._2)._1
  println(s"Bear doll goes to $bearWinner")
  val bearWinnerTransactions = transByCust.lookup(bearWinner)
  println(s"Transactions made by him: ${bearWinnerTransactions.map(_.mkString(" ")).mkString("\n")}")
}
