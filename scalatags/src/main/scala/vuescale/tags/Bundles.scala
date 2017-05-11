package vuescale.tags

import scala.language.implicitConversions
import scalatags.{ generic, text, Text }

/** Utilities shared by tag builder implementations.
  */
trait VTagBundle[Builder, Output <: FragT, FragT] {

  implicit def unwrapAttrStack(
      attrStack: AttrStack[Builder, Output, FragT]): generic.Attr = attrStack.toAttr

  protected[this] def createAttr(
      name: String, raw: Boolean = true): generic.Attr
  protected[this] def createTag(
      s: String, void: Boolean = false): generic.TypedTag[Builder, Output, FragT]
}

/** Provide Vue.js custom tags & directives for
  * [[scalatags.Text]] package.
  *
  * This module likely be used when building inline template with strings.
  */
object Template {

  trait Provider extends VTagBundle[text.Builder, String, String] {

    protected[this] def createAttr(name: String, raw: Boolean = true) =
      Text.all.attr(name, raw = raw)

    protected[this] def createTag(s: String, void: Boolean = false) =
      Text.all.tag(s, void)
  }

  object bundle
    extends Provider
    with VueTags[text.Builder, String, String]
    with VueDirectives[text.Builder, String, String]
    with VueAttrs[text.Builder, String, String]

  object vueDirectives
    extends Provider
    with VueDirectives[text.Builder, String, String]
    with VueAttrs[text.Builder, String, String]
}
