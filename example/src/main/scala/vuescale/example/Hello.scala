package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.facades._
import vuescale.ext._

@JSExport
object Hello {

  @JSExport("Greeting") @JSExportAll
  case class Greeting(message: String)

  @JSExport
  def main(): Unit = {

    val opts = new ComponentOptions[Vue] {
      override val el = "#app"
      val data = { () =>
        js.Dictionary(
          "morning" -> Greeting("Ohayo"),
          "afternoon" -> Greeting("Konnichiwa"),
          "sleep" -> Greeting("Oyasumi")
        )
      }
    }
    val vm: Vue = new Vue(opts)
  }
}