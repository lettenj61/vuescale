package vuescale

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation._

import org.scalajs.dom
import org.scalajs.dom.window.console
import org.scalatest.FunSpec

import vuescale.prelude._
import vuescale.tags.syntax._

/** Test suite for Scala specific DSL features.
 */
class TagDSLSpec extends FunSpec {
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
            ^.onClick := { (e: dom.MouseEvent) => console.log("%o", e) }
          )
        )

      console.log("%o", vtag.decodeModifiers._2)

      val options = Component.builder[Vue with Box]("vtag-test")
        .data(new Box("large"))
        .renderTags { (vm: Vue with Box) => vtag }
        .build
      
      try {
        val vm = new Vue(options).$mount()
        console.log(vm.$el.outerHTML)
        vm.$emit("click")
      } catch {
        case e: Exception => throw e
      }
    }
  }
}