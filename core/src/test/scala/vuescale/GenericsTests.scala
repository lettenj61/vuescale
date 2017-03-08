package vuescale

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import ext._
import facades.generic._
import facades.{ Vue, ComponentOptions }

import js.annotation._
import test.MyComputation

class GenericsTests extends FunSuite {

  test("Infer") {
    val getter: Computed.Getter[Vue, Int] = (vm: Vue) => vm.getOrElse("x", 70)
    val fake: Vue = js.Dynamic.literal(x = 100).asInstanceOf[Vue]
    assert(getter(fake) === 100)
  }

  test("Computation revisited") {
    val opts = new ComponentOptions[Vue] {
      val data = js.Dynamic.literal(x = 200, name = "Ronald Buddy")
      computed = js.defined((new MyComputation).asInstanceOf[js.Dictionary[js.Any]])
    }
    val vm = new Vue(opts)
    assert(vm("bindValue") === "RONALD BUDDY")
  }
}