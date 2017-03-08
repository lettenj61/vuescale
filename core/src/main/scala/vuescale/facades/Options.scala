// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
abstract class ComponentOptions[V <: Vue] extends Object {

  type LifecycleHook = UndefOr[ThisFunction0[V, Unit]]
  type OptionalFns = UndefOr[Dictionary[js.Function]]

  // -- data options
  val data: Any

  var props: UndefOr[Any] = undefined // FIXME can give any clearer type?
  var propsData: UndefOr[Object] = undefined
  var computed: Any = undefined
  var methods: UndefOr[Dictionary[Any]] = undefined

  // -- dom options
  val el: UndefOr[dom.html.Element | String] = undefined
  val template: UndefOr[String] = undefined
  var render: UndefOr[ThisFunction1[V, js.Function, VNode]] = undefined
  var renderError: UndefOr[js.Function2[CreateElement.Empty, js.Error, VNode]] = undefined
  var staticRenderFns: UndefOr[Array[js.Function]] = undefined

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

  // -- components
  val directives: OptionalFns = undefined
  val components: OptionalFns = undefined

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

object ComputedOptions {

  def apply[V](get: UndefOr[ThisFunction0[V, Any]] = undefined,
              set: UndefOr[ThisFunction1[V, Any, Unit]] = undefined,
              cache: UndefOr[Boolean] = undefined)
              : ComputedOptions[V] =
    applyProxy(get, set, cache)

  private[this] def applyProxy[V](_get: UndefOr[ThisFunction0[V, Any]] = undefined,
                                  _set: UndefOr[ThisFunction1[V, Any, Unit]] = undefined,
                                  _cache: UndefOr[Boolean] = undefined)
                                  : ComputedOptions[V] = new ComputedOptions[V] {
    override val get = _get
    override val set = _set
    override val cache = _cache
  }
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
