package vuescale.test

import scala.scalajs.js
import js._
import js.annotation._

import vuescale.facades.raw.Vue
import vuescale.facades.generic.{ Accessor, Computed }

@ScalaJSDefined
class MyComputation extends js.Object {
  val counter: Computed.Getter[Vue, Int] = (vm: Vue) => vm.getOrElse("x", 100)
  val bindValue: Accessor[Vue, String] = Accessor(
    get = { (vm: Vue) => vm.getOrElse("name", "").toUpperCase },
    set = { (vm: Vue, s: String) => vm.update("name", s.toLowerCase) }
  )
}

@JSExportAll
case class Person(firstName: String, lastName: String)
@ScalaJSDefined
class Patron(val member: js.Array[Person]) extends js.Object
