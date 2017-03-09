package vuescale

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import facades.generic._

import js.annotation._
import test.MyComputation

class GenericsTests extends FunSuite {

  test("Infer") {
    type Vue = facades.raw.Vue
    val getter: Computed.Getter[Vue, Int] = (vm: Vue) => vm.getOrElse("x", 70)
    val fake: Vue = js.Dynamic.literal(x = 100).asInstanceOf[Vue]
    assert(getter(fake) === 100)
  }

  test("Computation revisited") {
    type TestView = Vue[js.Dynamic, MyComputation]
    val opts = new ComponentOptions[js.Dynamic, MyComputation] {
      override val data = js.defined {() =>
        js.Dynamic.literal(x = 200, name = "Ronald Buddy")
      }
      override val computed = js.defined(new MyComputation)
    }
    val vm = new Vue(opts)
    assert(vm("bindValue") === "RONALD BUDDY")
  }

  test("Real world case?") {
    // First, I have some data to bind into rendered page
    import vuescale.test.{ Person, Patron }

    val patrons = js.Array(
      Person("Mr.", "Foo"), Person("Mrs.", "Bar"), Person("Sundown", "Kid")
    )

    @ScalaJSDefined
    class Sales extends js.Object {
      val moneyMaker: Computed.Getter[Vue[Patron, Sales], String] = {(vm: Vue[Patron, Sales]) =>
        val head = vm.proxy.member(0)
        head.firstName + " " + head.lastName
      }
    }

    val opts = new ComponentOptions[Patron, Sales] {
      override val data = js.defined { () => new Patron(patrons) }
      override val computed = new Sales
    }

    val vm = new Vue(opts)
    assert(vm("moneyMaker") === "Mr. Foo")
  }
}