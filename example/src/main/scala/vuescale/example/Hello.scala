package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.facades._, raw._

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @JSExportTopLevel("Greeting") @JSExportAll
  case class Greeting(message: String)

  @JSExport
  def main(): Unit = {

    val opts = new ComponentOptions {
      el = "#app"
      override val data = Bindings[Any](
        "morning" -> Greeting("Ohayo"),
        "afternoon" -> Greeting("Konnichiwa"),
        "sleep" -> Greeting("Oyasumi"),
        "treasures" -> js.Array(120, 460, 1080, 9600)
      )
    }
    val vm: Vue = new Vue(opts)
  }
}
