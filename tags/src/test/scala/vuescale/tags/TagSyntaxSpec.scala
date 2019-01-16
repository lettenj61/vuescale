package vuescale.tags

import org.scalajs.dom
import org.scalajs.dom.window.console
import org.scalatest.FunSpec
import vuescale.prelude._
import vuescale.scaladsl.Component
import vuescale.tags.syntax._
import vuescale.tags.syntax.prefixed._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

/** Test suite for Scala specific DSL features.
 */
class TagSyntaxSpec extends FunSpec {
  describe("Tags DSL") {
    it ("constructs a tree") {
      val nodes = <.div(^.id := "one")
      console.log(nodes.toString)
      val (text, opts) = nodes.decodeModifiers 
      console.log("%o", text.orUndefined, opts)
    }

    it ("can render a component") {
      class Box(var label: String) extends js.Object
      val vtag =
        <.div(^.name := "test", ^.hidden)(
          <.button(
            ^.onClick := { e: dom.MouseEvent => console.log("%o", e) },
            "press me!"
          )
        )

      console.log("%o", vtag.decodeModifiers._2)

      val options = Component.builder[Vue with Box]("vtag-test")
        .data(new Box("large"))
        .render(vtag)
        .build
      
      try {
        val vm = new Vue(options).$mount()
        assert(vm.$el.outerHTML == "<div name=\"test\" hidden=\"hidden\"><button>press me!</button></div>")
        vm.$emit("click")
      } catch {
        case e: Exception => throw e
      }
    }
  }
}