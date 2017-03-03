// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
class ComponentOptions[V <: Vue](
    val el: UndefOr[dom.html.Element | String] = undefined,
    val _model: Any = undefined
) extends Object {

  type LifecycleHook = UndefOr[ThisFunction0[V, Unit]]

  // -- data options
  var data: js.Function0[Any] = () => _model
  var props: UndefOr[Any] = undefined // FIXME can give any clearer type?
  var propsData: UndefOr[Object] = undefined
  var computed: UndefOr[Dictionary[ThisFunction0[Vue, _]]] = undefined
  var methods: UndefOr[Dictionary[ThisFunction1[Vue, Array[_], _]]] = undefined

  // -- dom options
  val template: UndefOr[String] = undefined
  // TODO: render
  // TODO: renderError
  // TODO: staticRenderFns

  // -- lifecycle hooks
  var beforeCreate: LifecycleHook = undefined
  var created: LifecycleHook = undefined
  var beforeDestroy: LifecycleHook = undefined
  var destroyed: LifecycleHook = undefined
  var beforeMount: LifecycleHook = undefined
  var mounted: LifecycleHook = undefined
  var beforeUpdate: LifecycleHook = undefined
  var updated: LifecycleHook = undefined
  var activated: LifecycleHook = undefined
  var deactivated: LifecycleHook = undefined

  var parent: UndefOr[Vue] = undefined
  // TODO: `mixins`
  var name: UndefOr[String] = undefined
  // TODO: `extends`
  var delimiters: UndefOr[(String, String)] = undefined
}

@ScalaJSDefined
trait ComponentOptionsFn extends Object {
  var props: UndefOr[Any] = undefined // FIXME: clearer type
  val functional: Boolean
  val render: ThisFunction2[Nothing, js.Function, RenderContext, VNode]
  val name: UndefOr[String] = undefined
}

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
class PropOptions extends Object {
  val `type`: UndefOr[Any] = undefined // FIXME name and type
  val required: UndefOr[Boolean] = undefined
  val default: UndefOr[Any] = undefined
  val validator: UndefOr[js.Function1[Any, Boolean]] = undefined
}

@ScalaJSDefined
trait ComputedOptions[V] extends Object {
  val get: UndefOr[ThisFunction0[V, Any]] = undefined
  val set: UndefOr[ThisFunction1[V, Any, Unit]] = undefined
  val cache: UndefOr[Boolean] = undefined
}

@ScalaJSDefined
trait WatchOptions extends Object {
  val deep: UndefOr[Boolean] = undefined
  val immediate: UndefOr[Boolean] = undefined
}

@ScalaJSDefined
abstract class DirectiveOptions extends Object {
  var bind: UndefOr[DirectiveFunction] = undefined
  var inserted: UndefOr[DirectiveFunction] = undefined
  var update: UndefOr[DirectiveFunction] = undefined
  var componentUpdated: UndefOr[DirectiveFunction] = undefined
  var unbind: UndefOr[DirectiveFunction] = undefined
}
