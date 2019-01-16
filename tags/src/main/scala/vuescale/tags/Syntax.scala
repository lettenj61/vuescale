package vuescale
package tags

import vuescale.facade._
import vuescale.scaladsl.Builder
import vuescale.tags.internal._

import scala.language.implicitConversions
import scala.scalajs.js

object syntax {

  type VTag = internal.VTag
  type VTagSeq = internal.VTagSeq
  type VModifier = internal.VModifier
  type VAttribute = internal.VAttribute
  type VBinding = internal.VBinding
  type VDomProp = internal.VDomProp
  type VEventListener = internal.VEventListener

  type ClassMap = internal.ClassMap
  def ClassMap(classDefs: (String, Boolean)*): js.Dictionary[Boolean] =
    js.Dictionary(classDefs: _*)

  trait ModifierSyntax
      extends KeysAndBindings
         with Attributes
         with DomProps
         with Events

  object naked extends Tags with LowPriorityTags with ModifierSyntax
  object prefixed {
    @inline final def < : Tags with LowPriorityTags = naked
    @inline final def ^ : ModifierSyntax = naked
  }

  implicit val TextNode: String => VModifier =
    (text: String) => VText(text)

  implicit def SeqTag(xs: Seq[VTag]): VTagSeq =
    VTagSeq(xs)

  implicit def ArrayTag(xs: js.Array[VTag]): VTagSeq =
    VTagSeq(xs.seq)

  implicit def bindVoidAttribute(attr: VoidAttribute): VAttribute =
    attr := true

  // experimental
  implicit def orphanBoolAttr(attr: AttributeKey[Boolean]): VAttribute =
    attr := true

  implicit def vtagIsRenderFn(vtag: VTag): RenderFunction =
    vtag.renderFn

  implicit def renderVTagWith[V <: Vue](f: (V, CreateElement) => VTag): RenderThisFunction[V] =
    (vm, h) => f(vm, h).renderFn(h)

  implicit class BuilderExt[V](val self: Builder[V]) extends AnyVal {
    @inline def renderTags(factory: V => VTag): self.type =
      self.render((vm: V, h: CreateElement) => factory(vm).renderFn(h))
  }
}