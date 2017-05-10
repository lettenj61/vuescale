package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.prelude._

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @ScalaJSDefined
  class GreetingPage extends js.Object {
    val isChecked: Boolean = true
    val treasures: js.Array[Int] = js.Array(120, 460, 1080, 9600)
    val greets = js.Dictionary(
      "morning" -> "Ohayo", "afternoon" -> "Konnichiwa",
      "night" -> "Konbanwa", "sleep" -> "Oyasumi"
    )
  }

  @JSExport
  def main(): Unit = {

    val opts = new ComponentOptions[Vue, GreetingPage](
      el = "#app",
      initData = new GreetingPage
    )
    val vm: Vue = new Vue(opts.build())
  }
}
