package vuescale

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import facades.raw._
import facades.generic.Computed

/** Basic interactions to Vue.js
  *
  * Ideas are come from:
  * https://github.com/vuejs/vue/tree/dev/test/unit
  */
class VmInteractionTests extends FunSuite {
  import js.Dynamic.literal

  test("Access data through VM proxy") {
    val vm: Vue = new Vue(ComponentOptions(
      data = literal(
        "foo" -> "bar",
        "baz" -> "quux"
      )
    ))
    assert(vm("foo") === "bar")
    assert(vm("baz") === "quux")
    vm.update("foo", 100)
    assert(vm("foo") === 100)
  }

  test("`$data` property") {
    val sample = literal("a" -> 100)
    val opts = new ComponentOptions {
      override val data = js.defined(() => sample)
    }
    val vm = new Vue(opts)
    assert(vm.$data.a === 100)
    assert(vm.$data.a === vm("a"))
    vm.update("a", -1)
    assert(sample.a === -1)
  }

  test("Computations") {
    val x = literal("a" -> 2)
    val f: Computed.Getter[Vue, String] = {(vm: Vue) =>
      val i = (vm.getOrElse("a", 0) * 1000)
      s"${i}kg"
    }
    val opts = ComponentOptions(
      data = x,
      computed = js.Dictionary("kilogram" -> f)
    )
    val vm = new Vue(opts)
    assert(vm("kilogram") === "2000kg")
    x.a = 8
    assert(vm("kilogram") === "8000kg")
  }
}