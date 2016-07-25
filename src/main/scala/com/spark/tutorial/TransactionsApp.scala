package com.spark.tutorial

import com.spark.tutorial.SparkCommon._

case class Product(id: Int, name: String, price: Float, rank: Int)
case class Transaction(date: String, customerId: Int, productId: Int, amount: Int, discount: Float)

object TransactionsApp extends App {
  val productsFile = sc.textFile(args(0))
  val transactionsFile = sc.textFile(args(1))

  val products = productsFile.map { line =>
    val array = line.split("#")
    val id = array(0).toInt
    (id, Product(id, array(1), array(2).toFloat, array(3).toInt))
  }
  val transactions = transactionsFile.map { line =>
    val array = line.split("#")
    val date = s"${array(0)} ${array(1)}"

    val id = array(3).toInt
    (id, Transaction(date, array(2).toInt, id, array(4).toInt, array(5).toFloat))
  }

  val sameProductTransactions = transactions.groupByKey()

  val boughtProducts = products.join(sameProductTransactions)
  boughtProducts.foreach {
    case (_, (Product(prodName, _, _, _), prodTransactions: Iterable[Transaction])) => println(s"$prodName bought by ${prodTransactions.map(_.customerId).mkString(", ")}")
  }
}
