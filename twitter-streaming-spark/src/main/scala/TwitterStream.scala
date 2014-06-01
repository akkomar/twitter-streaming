package akkomar.twitter

import twitter4j._
import com.typesafe.config.ConfigFactory

object StatusStreamer {
  def main(args: Array[String]) {
    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
    twitterStream.addListener(Util.simpleStatusListener)
    twitterStream.sample
    Thread.sleep(2000)
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}

object Util {
  val conf = ConfigFactory.load("twitter")
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(conf.getString("consumerKey"))
    .setOAuthConsumerSecret(conf.getString("consumerSecret"))
    .setOAuthAccessToken(conf.getString("accessToken"))
    .setOAuthAccessTokenSecret(conf.getString("accessTokenSecret"))
    .build

  def simpleStatusListener = new StatusListener() {
    def onStatus(status: Status) {
      println(status.getText)
    }

    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}

    def onException(ex: Exception) {
      ex.printStackTrace
    }

    def onScrubGeo(arg0: Long, arg1: Long) {}

    def onStallWarning(warning: StallWarning) {}
  }
}