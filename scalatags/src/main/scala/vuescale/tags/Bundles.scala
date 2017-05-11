package vuescale.tags

import scala.language.implicitConversions
import scalatags.{ generic, text }

/** Utilities shared by tag builder implementations.
  */
trait VTagBundle[Builder, Output <: FragT, FragT] {

  def bundle: generic.Bundle[Builder, Output, FragT]

  implicit def unwrapAttrStack(
      attrStack: internal.AttrStack[Builder, Output, FragT]): generic.Attr = attrStack.toAttr
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
    val bundle = scalatags.Text
  }

  object bundle
    extends Provider
    with VueAttrs
    with VueDirectives
    with VueTags

  object vueDirectives
    extends Provider
    with VueAttrs
    with VueDirectives
}
