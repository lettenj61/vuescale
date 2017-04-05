// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
class RenderContext(
  val props: Any,
  val children: Array[VNode],
  val data: VNodeData,
  val parent: Vue
) extends Object {

  def slots(): Any = undefined
}

@ScalaJSDefined
trait WatchOptions extends Object {
  val deep: UndefOr[Boolean] = undefined
  val immediate: UndefOr[Boolean] = undefined
}

@ScalaJSDefined
trait DirectiveOptions extends Object {
  var bind: UndefOr[DirectiveFunction] = undefined
  var inserted: UndefOr[DirectiveFunction] = undefined
  var update: UndefOr[DirectiveFunction] = undefined
  var componentUpdated: UndefOr[DirectiveFunction] = undefined
  var unbind: UndefOr[DirectiveFunction] = undefined
}
