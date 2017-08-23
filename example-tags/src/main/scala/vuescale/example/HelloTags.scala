package vuescale.example

import scala.scalajs.js
import js.Dynamic.literal
import js.annotation._

import org.scalajs.dom

import vuescale.prelude._

@JSExportAll
case class Treasure(name: String, price: Int)

@JSExportTopLevel("vuescale.example.HelloTags")
object HelloTags {

  @JSExport
  var vm: js.UndefOr[Vue] = js.undefined

  @ScalaJSDefined
  class GreetingPage extends js.Object {
    val greet = "Hello, Vue.js"
    val treasures: js.Array[Treasure] = js.Array(
      Treasure("Gold piece", 799),
      Treasure("Silver ring", 1499),
      Treasure("Book of useful techniques", 6499),
      Treasure("Old game console", 24999)
    )
  }

  @JSExport
  def main(): Unit = {
    dom.document.getElementById("wrapper").appendChild(Components.Home.render)
    vm = VueModel.withData(
      el = "#app",
      data = new GreetingPage
    )
  }
}
