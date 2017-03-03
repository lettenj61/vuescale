package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.core.facades._

@JSExport
object Hello {

  @JSExport("Greeting") @JSExportAll
  case class Greeting(message: String)

  @JSExport
  def main(): Unit = {

    val model = js.Dictionary(
      "morning" -> Greeting("Ohayo"),
      "afternoon" -> Greeting("Konnichiwa"),
      "sleep" -> Greeting("Oyasumi")
    )
    val opts = new ComponentOptions[Vue] {
      override val el = js.defined("#app")
      override val data = { () => model }
    }
    val vm: Vue = new Vue(opts)
  }
}