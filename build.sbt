name := "akka-quickstart-java"

version := "1.0"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "junit" % "junit" % "4.12",
  "io.kamon" %% "kamon-core" % "1.1.0",
  "io.kamon" %% "kamon-logback" % "1.0.0",
  "io.kamon" %% "kamon-akka-2.5" % "1.0.1",
  "io.kamon" %% "kamon-prometheus" % "1.0.0",
  "io.kamon" %% "kamon-zipkin" % "1.0.0")

resolvers += Resolver.bintrayRepo("kamon-io", "sbt-plugins")
addSbtPlugin("io.kamon" % "sbt-aspectj-runner" % "1.1.0")
