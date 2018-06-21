package vuescale

import scala.scalajs.js

import org.scalajs.dom

package object tags {
  type VTag = internal.VTag
  val VTag: internal.VTag.type = internal.VTag

  type VModifier = internal.VModifier

  type VAttribute = internal.VAttribute
  val VAttribute: internal.VAttribute.type = internal.VAttribute

  type VBinding = internal.VBinding
  val VBinding: internal.VBinding.type = internal.VBinding

  type VDomProp = internal.VDomProp
  val VDomProp: internal.VDomProp.type = internal.VDomProp

  type VEventListener[E <: dom.Event, R] = internal.VEventListener[E, R]
  val VEventListener: internal.VEventListener.type = internal.VEventListener
}