
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
  testFrameworks += new TestFramework("utest.runner.Framework"),

  scalacOptions in Compile ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  )
)

val domSettings = Seq(
  requiresDOM := true,
  jsEnv := PhantomJSEnv().value
)

lazy val core = project
  .enablePlugins(ScalaJSPlugin)
  .settings((commonSettings ++ domSettings): _*)
  .settings(
    name := "vuescale-core",
    jsDependencies += "org.webjars" % "vue" % "2.1.3" / "2.1.3/vue.js" % Test
  )

lazy val scalatags = project.in(file("scalatags"))
  .dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings((commonSettings ++ domSettings): _*)
  .settings(
    name := "vuescale-scalatags",
    libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.6.5"
  )

lazy val example = project.dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "vuescale-example"
  )
