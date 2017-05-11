
val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.12.2"

crossScalaVersions := Seq("2.10.6", "2.11.11", "2.12.2")

val commonSettings = Seq(
  organization := "com.github.lettenj61",
  version := libV,
  scalaVersion := scalaV,

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "com.lihaoyi" %%% "utest" % "0.4.6" % Test
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
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
