package vuescale.example

import scala.scalajs.js
import scala.scalajs.js.annotation._

import vuescale.prelude._

@JSExportAll
case class Treasure(name: String, price: Int)

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @ScalaJSDefined
  class Example extends js.Object {
    val greet = "Hello, Vue.js"
    val treasures: js.Array[Treasure] = js.Array(
      Treasure("Gold piece", 799),
      Treasure("Silver ring", 1499),
      Treasure("Book of useful techniques", 6499),
      Treasure("Old game console", 24999)
    )
  }

  type ExampleView = Vue with Example 

  @JSExport
  def main(): Unit = {
    val vm: Vue = new Vue(Component[ExampleView](
      el = "#app",
      data = new Example,
      computed = new Handler[ExampleView] {
        val sumOfTreasures: Callback0[Int] = { (vm: ExampleView) =>
          vm.treasures.map(_.price).sum
        }
      }
    ))
  }
}
