package vuescale

import scala.language.implicitConversions

import scala.scalajs.js
import js.annotation._

import org.scalajs.dom
import vuescale.core.facades

object prelude {

  type Vue[A] = facades.Vue[A]
  val Vue: facades.VueStatic = js.Dynamic.global.Vue.asInstanceOf[facades.VueStatic]

  type Directive = facades.Directive
  implicit def FunctionDirective(
      f: js.Function2[dom.html.Element, VNodeDirective, Unit]): Directive = {

    f.asInstanceOf[Directive]
  }

  type ScopedSlot = facades.ScopedSlot

  type VNode = facades.VNode
  type VNodeData = facades.VNodeData
  type VNodeDirective = facades.VNodeDirective

  type ComponentOptions[A] = facades.ComponentOptions[A]

  final def Options[D](_el: String, _data: D): ComponentOptions[D] = new ComponentOptions[D] {
    el = _el: js.UndefOr[String]
    data = _data: js.UndefOr[D]
  }

  final def VueModel[D](el: String, data: D): Vue[D] = {
    val options = Options(el, data)
    new Vue(options)
  }
}
