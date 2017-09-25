package vuescale.tags

import scalatags.Text
import vuescale.tags.Template

import org.scalatest.FunSpec

class TextSpec extends FunSpec {

  import Template.all._
  import Text.all._

  describe("AttrStacks") {

    it("emits directive with arguments") {
      val tpl = div(vOn.click.prevent:="handle").render
      assert(tpl == "<div v-on:click.prevent=\"handle\"></div>")
    }

    it("emits custom argument") {
      val tpl = p(vBind("holy-cow") := "15").render
      assert(tpl == "<p v-bind:holy-cow=\"15\"></p>")
    }

    it("can emit custom directive") {
      val cool = directive("cool")
      val tpl = p(cool("hot").modify(_.hit.the.road) := "99").render
      assert(tpl == "<p v-cool:hot.hit.the.road=\"99\"></p>")
    }

    it("can append modifiers in arguments") {
      val myDirective = vOn("accident").append("foo", "kek", "smaack")
      val tpl = button(myDirective := "func1").render
      assert(tpl == "<button v-on:accident.foo.kek.smaack=\"func1\"></button>")
    }
  }
}
