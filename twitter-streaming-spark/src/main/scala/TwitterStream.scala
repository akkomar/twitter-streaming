import twitter4j._
import com.typesafe.config.ConfigFactory

object StatusStreamer {
  def main(args: Array[String]) {
    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
    twitterStream.addListener(Util.simpleStatusListener)
    twitterStream.sample()
    Thread.sleep(1000 * 60)
    twitterStream.cleanUp()
    twitterStream.shutdown()
  }
}
