package vuescale
package tags

import scala.language.implicitConversions
import scala.scalajs.js

import vuescale.tags.internal._

object syntax {
  trait ModifierSyntax extends KeysAndBindings
                          with Attributes
                          with DomProps
                          with Events

  object ^ extends ModifierSyntax
  object < extends Tags

  implicit val TextNode: (String => VModifier) =
    (text: String) => VText(text)

  implicit def bindVoidAttribute(attr: VoidAttribute): VAttribute =
    attr := true
}