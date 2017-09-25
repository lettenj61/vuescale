package vuescale.tags

import scalatags.JsDom.all._
import vuescale.tags.Dom.all._

import org.scalatest.FunSpec

class JsDomSpec extends FunSpec {

  describe("directives") {
    it("emits `v-if` attribute") {
      val tpl = div(
        template(vIf:="condition")(
          ul(li(vFor:="a in b", "{{ a }}"))
        )
      ).render
      assert(tpl.outerHTML == "<div><template v-if=\"condition\"><ul><li v-for=\"a in b\">{{ a }}</li></ul></template></div>")
    }
  }

}
