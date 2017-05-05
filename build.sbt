name := "HotelSystems"

version := "1.0"

lazy val `hotelsystems` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies += filters

libraryDependencies += javaJdbc

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1104-jdbc41"

libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.1-P25-B3"

libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
