package vuescale
package facade

import scala.scalajs.js

@js.native
trait CreateElement extends js.Object {

  def apply(): VNode
  def apply(tag: String): VNode
  def apply(tag: js.Object): VNode
  def apply(tag: String, vnodeChildren: String): VNode
  def apply(tag: String, vnodeChildren: js.Array[_]): VNode

  def apply(
    tag: String,
    options: js.Any,
    vnodeChildren: String
  ): VNode

  def apply(
    tag: js.Object,
    options: js.Any,
    vnodeChildren: String
  ): VNode

  def apply(
    tag: String,
    options: js.Any,
    vnodeChildren: js.Array[_]
  ): VNode

  def apply(
    tag: js.Object,
    options: js.Any,
    vnodeChildren: js.Array[_]
  ): VNode
}