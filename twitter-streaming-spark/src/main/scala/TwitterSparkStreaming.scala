import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.auth.OAuthAuthorization

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TwitterSparkStreaming {
  def main(args: Array[String]) {

    val ssc = new StreamingContext("local[3]", "TwitterFeed", Seconds(1))
    ssc.checkpoint("checkpoint/")

    val tweets = TwitterUtils.createStream(ssc, Some(new OAuthAuthorization(Util.config)))
    //    val count = tweets.count()
    //    count.print()

    val statuses = tweets.map(_.getText)
    val words = statuses.flatMap(_.split("[\\s\\,]"))
    val hashtags = words.filter(_.startsWith("#"))
    //    hashtags.print()
    val counts = hashtags.map((_, 1)).reduceByKeyAndWindow(_ + _, _ - _, Seconds(60), Seconds(1))
    val sortedCounts = counts.map { case (tag, count) => (count, tag)}.transform(rdd => rdd.sortByKey(false))
    sortedCounts.foreachRDD(rdd => println("\nTop 10 hashtags:\n" + rdd.take(10).mkString("\n")))

    ssc.start()

//    Future {
//      Thread.sleep(1000 * 60 * 10);
//      ssc.stop(true, true)
//    }

    ssc.awaitTermination(1000)
  }
}
