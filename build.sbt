import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

val libV = "0.1.0-SNAPSHOT"
val scalaV = "2.12.3"
val vueV = "2.4.4"

crossScalaVersions := Seq("2.10.6", "2.11.11", "2.12.3")

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
    "-Xlint",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  )
)

val domSettings = Seq(
  requiresDOM in Test := true,
  jsEnv in Test := new JSDOMNodeJSEnv,
  jsDependencies ++= Seq(
    "org.webjars.npm" % "github-com-vuejs-vue" % vueV / "vue.js" % "test"
  )
)

lazy val core = project.in(file("vuescale-core"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vuescale-core"
  )

lazy val scalatags = project.in(file("vuescale-scalatags"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings, domSettings)
  .settings(
    name := "vuescale-scalatags",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % "0.6.7"
    )
  )

lazy val example = project.dependsOn(core, scalatags)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "vuescale-example"
  )
