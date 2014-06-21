Run simple twitter4j listener, outputting tweets to console:
$ sbt 'run-main StatusStreamer'

Run Spark Streaming application which will print 10 most popular twitter hashtags from last 60 s, updating every second.
$ sbt 'run-main TwitterSparkStreaming'