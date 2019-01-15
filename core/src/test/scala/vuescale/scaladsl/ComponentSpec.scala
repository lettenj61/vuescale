package vuescale.scaladsl

import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits._
import scala.scalajs.concurrent.JSExecutionContext

import org.scalatest.{ AsyncFunSpec, BeforeAndAfter }

import vuescale.prelude._

/*
 * Port from:
 * https://github.com/vuejs/vue/blob/dev/test/unit/features/component/component.spec.js
 */

class ComponentSpec extends AsyncFunSpec with BeforeAndAfter {

  // Must override here to use correct execution context in JS
  // @see https://github.com/scalatest/scalatest/issues/1039
  implicit override def executionContext =
    JSExecutionContext.queue

  class Data[A](val a: A) extends js.Object
  def Data[A](arg: A): Data[A] = new Data(arg)

  // FIXME: BeforeAndAfter would not work with async tests on Scala.js
  // https://stackoverflow.com/questions/46584633/async-before-in-scalatest-for-scalajs
  before {
    Vue.config.silent = true
    Vue.config.productionTip = false
  }

  describe("Component") {
    it("static") {
      val vm = new Vue(
        Component.builder
          .template("<test></test>")
          .components(
            "test" -> Component(
              data = Data(123),
              template = "<span>{{a}}</span>"
            )
          )
          .build
      ).$mount()

      assert(vm.$el.tagName == "SPAN")
      assert(vm.$el.innerHTML == "123")
    }

    it("using component in restricted elements") {
      val vm = new Vue(
        Component.builder
          .template("<div><table><tbody><test></test></tbody></table></div>")
          .components(
            "test" -> Component(
              data = Data(123),
              template = "<tr><td>{{a}}</td></tr>"
            )
          )
          .build
      ).$mount()

      assert(vm.$el.innerHTML == "<table><tbody><tr><td>123</td></tr></tbody></table>")
    }

    it("`is` attribute") {
      val vm = new Vue(
        Component.builder
          .template("<div><table><tbody><tr is=\"test\"></tr></tbody></table></div>")
          .components(
            "test" -> Component(
              data = Data(123),
              template = "<tr><td>{{a}}</td></tr>"
            )
          )
          .build
      ).$mount()

      assert(vm.$el.innerHTML == "<table><tbody><tr><td>123</td></tr></tbody></table>")
    }

    it("inline template") {
      val vm = new Vue(
        Component.builder
          .template("<div><test inline-template><span>{{a}}</span></test></div>")
          .data(Data("parent"))
          .components(
            "test" -> Component(
              data = Data("child")
            )
          )
          .build
      ).$mount()

      assert(vm.$el.innerHTML == "<span>child</span>")
    }

    // TODO: fragment instance warning

    class TestView(val view: String) extends js.Object

    it("dynamic") {
      val vm = new Vue(
        Component.builder
          .template("<component :is=\"view\" :view=\"view\"></component>")
          .data("view" -> "view-a")
          .components(
            "view-a" -> Component(
              template = "<div>foo {{view}}</div>",
              data = new TestView("a")
            ),
            "view-b" -> Component(
              template = "<div>bar {{view}}</div>",
              data = new TestView("b")
            )
          )
          .build
      ).$mount()

      assert(vm.$el.outerHTML == "<div view=\"view-a\">foo a</div>")
      vm("view") = "view-b"
      vm.$nextTick().map { _ =>
        assert(vm.$el.outerHTML == "<div view=\"view-b\">bar b</div>")
        vm("view") = ""
      } map { _ =>
        val data = vm.$el.asInstanceOf[js.Dynamic].data.toString
        assert(vm.$el.nodeType == 8)
        assert(data == "")
      }
    }

  }

}