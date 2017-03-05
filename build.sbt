
val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.12.1"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

val commonSettings = Seq(
  organization := "com.github.lettenj61",
  version := libV,
  scalaVersion := scalaV,

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "org.scalatest" %%% "scalatest" % "3.0.1" % Test
  )
)

lazy val core = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "vuescale-core",
    requiresDOM := true,
    jsEnv := PhantomJSEnv().value,
    jsDependencies += "org.webjars" % "vue" % "2.1.3" / "2.1.3/vue.js" % Test
  )

lazy val example = project.dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "vuescale-example"
  )
