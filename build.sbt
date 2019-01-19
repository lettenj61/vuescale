import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.12.8"
val vueV = "2.5.22"

crossScalaVersions := Seq("2.10.6", "2.11.12", "2.12.8")

val commonSettings = Seq(
  organization := "com.github.lettenj61",
  version := libV,
  scalaVersion := scalaV,

  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.6",
    "org.scalatest" %%% "scalatest" % "3.0.5" % "test"
  ),

  scalacOptions in Compile ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ypartial-unification",
    "-P:scalajs:sjsDefinedByDefault"
  )
)

val domSettings = Seq(
  jsEnv in Test := new JSDOMNodeJSEnv,
  jsDependencies ++= Seq(
    // use minified, compiler+runtime build for tests
    "org.webjars.npm" % "vue" % vueV / "dist/vue.min.js" % "test"
  )
)

lazy val core = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vuescale-core"
  )

lazy val tags = project
  .dependsOn(core)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vuescale-tags"
  )

lazy val example = project.dependsOn(core, tags)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    name := "vuescale-example"
  )
