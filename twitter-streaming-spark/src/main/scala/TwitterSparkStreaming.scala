import org.apache.spark.streaming._
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.StreamingContext._

object TwitterSparkStreaming {
  def main(args: Array[String]) {
    val ssc = new StreamingContext("local", "TwitterFeed", Seconds(1))
//    val tweets = ssc.stream
  }
}

//class TwitterFeedReceiver extends Receiver
