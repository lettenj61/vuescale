
val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.11.8"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

val commonSettings = Seq(
  organization := "com.github.lettenj61",
  version := libV,
  scalaVersion := scalaV,

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  )
)

lazy val core = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
  name := "vuescale-core"
)
