package vuescale.tags

import org.scalatest.FunSpec
import vuescale.prelude._
import vuescale.tags.syntax._
import vuescale.tags.syntax.prefixed._

import scala.scalajs.js

class VTagSpec extends FunSpec {

  def checkComponent(vtag: VTag, expected: String) = {
    val vm = new Vue(Component(
      render = js.defined { h: CreateElement => vtag.renderFn(h) }
    )).$mount()
    assert(vm.$el.outerHTML == expected)
    vm.$el.outerHTML
  }

  describe("VTag") {
    it("renders HTML5 elements") {
      checkComponent(
        <.div("Hello", "World"),
        "<div>HelloWorld</div>"
      )

      checkComponent(
        <.blockquote(^.id := "block-quote"),
        "<blockquote id=\"block-quote\"></blockquote>"
      )

      // boolean orphan attribute
      checkComponent(
        <.input(^.typeName := "text", ^.contenteditable),
        "<input type=\"text\" contenteditable=\"true\">"
      )
    }
  }

}
