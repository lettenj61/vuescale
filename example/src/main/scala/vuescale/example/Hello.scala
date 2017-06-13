package vuescale.example

import scala.scalajs.js
import js.annotation._

import org.scalajs.dom

import vuescale.prelude._

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @ScalaJSDefined
  class GreetingPage extends js.Object {
    val isChecked: Boolean = true
    val treasures: js.Array[Int] = js.Array(120, 460, 1080, 9600)
    val greets = js.Dictionary(
      "morning" -> "Ohayo",
      "afternoon" -> "Konnichiwa",
      "night" -> "Konbanwa",
      "sleep" -> "Oyasumi"
    )
    var count: Int = 150
  }

  @JSExport
  def appStart(): Unit = {
    new Vue(createPageOptions())
  }

  @JSExport
  def createPageOptions(): js.Dynamic = {

    val builder = new ComponentBuilder("#app", new GreetingPage) {
      computed.sumOfTreasures = Bind(vm => vm.treasures.sum)
      computed.doubleCount = Bind(vm => vm.count * 2)
      methods.addTreasure = Bind { (vm: Context, i: Int) =>
        vm.treasures += (i * 460)
      }
      methods.countUp = Bind(vm => vm.count += 10)
    }

    builder.build
  }
}
