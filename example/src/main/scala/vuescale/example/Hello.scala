package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.prelude._

object Hello extends js.JSApp {

  @JSExport("Greeting") @JSExportAll
  case class Greeting(message: String)

  def main(): Unit = {

    val vm: Vue[js.Dictionary[Greeting]] = VueModel(
      el = "#app",
      data = js.Dictionary(
        "morning" -> Greeting("Ohayo"),
        "afternoon" -> Greeting("Konnichiwa"),
        "sleep" -> Greeting("Oyasumi")
      )
    )
  }
}