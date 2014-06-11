name := "twitter-streaming-spark"

version := "0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "1.0.0",
    "org.apache.spark" %% "spark-streaming" % "1.0.0",
    "org.apache.spark" %% "spark-streaming-twitter" % "1.0.0",
    "org.twitter4j" % "twitter4j-stream" % "3.0.3"//"4.0.1"
)
