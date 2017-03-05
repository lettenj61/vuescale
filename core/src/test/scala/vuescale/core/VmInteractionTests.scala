package vuescale.core

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import facades._

/** Basic interactions to Vue.js
  *
  * Ideas are come from:
  * https://github.com/vuejs/vue/tree/dev/test/unit
  */
class VmInteractionTests extends FunSuite with Helpers {
  import js.Dynamic.literal

  test("Access data through VM proxy") {
    val vm: Vue = vueWithoutId(
      "foo" -> "bar",
      "baz" -> "quux"
    )
    assert(vm("foo") === "bar")
    assert(vm("baz") === "quux")
    vm.update("foo", 100)
    assert(vm("foo") === 100)
  }

  test("`$data` property") {
    val sample = literal("a" -> 100)
    val opts = new ComponentOptions[Vue] {
      val data = sample
    }
    val vm = new Vue(opts)
    assert(vm.$data.a === 100)
    assert(vm.$data.a === vm("a"))
    vm.update("a", -1)
    assert(sample.a === -1)
  }
}