package vuescale.tags

import scala.language.implicitConversions
import scalatags.{ generic, text }

import org.scalajs.dom

import internal.{ AttrExtender, AttrStack }

/** Utilities shared by tag builder implementations.
  */
trait VTagBundle[Builder, Output <: FragT, FragT] {

  val bundle: generic.Bundle[Builder, Output, FragT]

  implicit def unwrapAttrStack(attrStack: AttrStack[Builder, Output, FragT]): generic.Attr =
    attrStack.toAttr

  implicit def unwrapExtender(ext: AttrExtender[Builder, Output, FragT]): generic.Attr =
    ext.base.toAttr
}

trait VTagAlias[Builder, Output <: FragT, FragT] {

  type VueAttrs = internal.VueAttrs[Builder, Output, FragT]
  type VueDirectives = internal.VueDirectives[Builder, Output, FragT]
  type VueTags = internal.VueTags[Builder, Output, FragT]
  
}

/** Provide Vue.js custom tags & directives for
  * [[scalatags.Text]] package.
  *
  * This module likely be used when building inline template with strings.
  */
object Template extends VTagAlias[text.Builder, String, String] {

  trait Provider extends VTagBundle[text.Builder, String, String] {
    lazy val bundle = scalatags.Text
  }

  object all
    extends Provider
    with VueAttrs
    with VueDirectives
    with VueTags

  object vueDirectives
    extends Provider
    with VueAttrs
    with VueDirectives
}

/** Vue.js directive / attribute integration for [[scalatags.JsDom]] package.
 */
object Dom extends VTagAlias[dom.Element, dom.Element, dom.Node] {

  trait Provider extends VTagBundle[dom.Element, dom.Element, dom.Node] {
    lazy val bundle = scalatags.JsDom
  }

  object all
    extends Provider
    with VueAttrs
    with VueDirectives
    with VueTags

  object vueDirectives
    extends Provider
    with VueAttrs
    with VueDirectives
}
