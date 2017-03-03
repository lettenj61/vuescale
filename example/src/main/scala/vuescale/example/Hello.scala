package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.core.facades._

object Hello extends js.JSApp {

  @JSExport("Greeting") @JSExportAll
  case class Greeting(message: String)

  def main(): Unit = {

    val model = js.Dictionary(
      "morning" -> Greeting("Ohayo"),
      "afternoon" -> Greeting("Konnichiwa"),
      "sleep" -> Greeting("Oyasumi")
    )
    val opts = new ComponentOptions[Vue]("#app", model)
    val vm: Vue = new Vue(opts)
  }
}