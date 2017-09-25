package vuescale

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

import org.scalatest.{BeforeAndAfter, FunSpec}

import vuescale.prelude._

/*
 * Port from:
 * https://github.com/vuejs/vue/blob/dev/test/unit/features/component/component.spec.js
 */

class ComponentSpec extends FunSpec with BeforeAndAfter {

  @ScalaJSDefined
  class Data[A](val a: A) extends js.Object

  def Data[A](arg: A): Data[A] = new Data(arg)

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
          .result
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
          .result
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
          .result
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
          .result
      ).$mount()

      assert(vm.$el.innerHTML == "<span>child</span>")
    }

    // TODO: fragment instance warning

    @ScalaJSDefined
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
          .result
      ).$mount()

      assert(vm.$el.outerHTML == "<div view=\"view-a\">foo a</div>")
      // TODO update component
    }

  }

}