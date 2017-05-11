import scalatags.Text
import vuescale.tags.Template

import utest._

object TextTests extends TestSuite {

  import Template.bundle._
  import Text.all._

  val tests = this {

    "attrStacks" - {
      "simply" - {
        val tpl = div(vOn.click.prevent:="handle").render
        assert(tpl == "<div v-on:click.prevent=\"handle\"></div>")
      }

      "customArgument" - {
        val tpl = p(vBind("holy-cow") := "15").render
        assert(tpl == "<p v-bind:holy-cow=\"15\"></p>")
      }

      "customDirective" - {
        val cool = directive("cool")
        val tpl = p(cool("hot").modify(_.hit.the.road) := "99").render
        assert(tpl == "<p v-cool:hot.hit.the.road=\"99\"></p>")
      }

      "appendModifiers" - {
        val myDirective = vOn("accident").append("foo", "kek", "smaack")
        val tpl = button(myDirective := "func1").render
        assert(tpl == "<button v-on:accident.foo.kek.smaack=\"func1\"></button>")
      }
    }
  }
}
