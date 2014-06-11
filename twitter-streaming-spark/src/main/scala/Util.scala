import com.typesafe.config.ConfigFactory
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}

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
