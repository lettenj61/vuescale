import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.12.4"
val vueV = "2.5.13"

crossScalaVersions := Seq("2.10.6", "2.11.11", "2.12.4")

val commonSettings = Seq(
  organization := "com.github.lettenj61",
  version := libV,
  scalaVersion := scalaV,

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.3",
    "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
  ),

  scalacOptions in Compile ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    // "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ypartial-unification",
    // "-Ywarn-dead-code",
    // "-Ywarn-extra-implicit",
    // "-Ywarn-inaccessible",
    // "-Ywarn-infer-any",
    // "-Ywarn-nullary-override",
    // "-Ywarn-nullary-unit",
    // "-Ywarn-numeric-widen",
    // "-Ywarn-unused:implicits",
    // "-Ywarn-unused:imports",
    // "-Ywarn-unused:locals",
    // "-Ywarn-unused:params",
    // "-Ywarn-unused:patvars",
    // "-Ywarn-unused:privates",
    // "-Ywarn-value-discard",
    "-P:scalajs:sjsDefinedByDefault"
  )
)

val domSettings = Seq(
  jsEnv in Test := new JSDOMNodeJSEnv,
  jsDependencies ++= Seq(
    "org.webjars.npm" % "vue" % vueV / "dist/vue.js" % "test"
  )
)

lazy val core = project.in(file("vuescale-core"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vuescale-core"
  )

lazy val example = project.dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "vuescale-example"
  )
