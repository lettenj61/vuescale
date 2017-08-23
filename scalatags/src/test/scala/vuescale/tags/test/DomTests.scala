package vuescale.tags.test

import scalatags.JsDom.all._
import vuescale.tags.Dom.all._

import utest._

object DomTests extends TestSuite {

  val tests = this {

    "directives" - {
      "ifElse" - {
        val tpl = div(
          template(vIf:="condition")(
            ul(li(vFor:="a in b", "{{ a }}"))
          )
        ).render
        assert(tpl.outerHTML == "<div><template v-if=\"condition\"><ul><li v-for=\"a in b\">{{ a }}</li></ul></template></div>")
      }
    }

  }
}
