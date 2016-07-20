name := """spark-tutorial"""

version := "1.0"

scalaVersion := "2.10.5"

lazy val spark = "1.6.0"

lazy val sparkTutorial = (project in file(".")).
  settings(
    name := "spark-in-action",
    version := "1.0",
    scalaVersion := "2.10.5",
    mainClass in Compile := Some("main.Main")
  )

// Spark related dependencies

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % spark,
  "org.apache.spark" %% "spark-sql" % spark,
  "org.apache.spark" %% "spark-streaming" % spark,
  "org.apache.spark" %% "spark-graphx" % spark,
  "com.databricks" %% "spark-csv" % "1.3.0"
)

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"



fork in run := true