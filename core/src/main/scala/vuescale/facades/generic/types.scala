package vuescale
package facades
package generic

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

/** Typed Vue.js reactive data.
  *
  * @tparam D   data type
  */
@ScalaJSDefined
trait ReactiveData[D] extends Object {
  val data: D
}

/** Typed Vue.js computed property.
  *
  * @tparam C   computed property type
  */
@ScalaJSDefined
trait ComputedProperty[C] extends Object {
  val computed: C
}

@ScalaJSDefined
trait DOMOptions extends Object {
  var el: UndefOr[dom.html.Element | String] = undefined
  var template: UndefOr[String] = undefined
}

@ScalaJSDefined
trait ComponentAssets extends Object {
  var directives: UndefOr[Dictionary[Any]] = undefined
  var filters: UndefOr[Dictionary[Any]] = undefined
  var components: UndefOr[Dictionary[Any]] = undefined
}

@ScalaJSDefined
trait ComponentLifecycle[V] extends Object {
  import ComponentOptions.LifecycleHook

  var beforeCreate: UndefOr[LifecycleHook[V]] = undefined
  var created: UndefOr[LifecycleHook[V]] = undefined
  var beforeDestroy: UndefOr[LifecycleHook[V]] = undefined
  var destroyed: UndefOr[LifecycleHook[V]] = undefined
  var beforeMount: UndefOr[LifecycleHook[V]] = undefined
  var mounted: UndefOr[LifecycleHook[V]] = undefined
  var beforeUpdate: UndefOr[LifecycleHook[V]] = undefined
  var updated: UndefOr[LifecycleHook[V]] = undefined
  var activated: UndefOr[LifecycleHook[V]] = undefined
  var deactivated: UndefOr[LifecycleHook[V]] = undefined
}

@ScalaJSDefined
trait GenOptions[V] extends ComponentLifecycle[V]
                       with DOMOptions
                       with ComponentAssets
{
  var methods: UndefOr[Dictionary[ThisFunction0[V, Unit]]] = undefined
  var props: UndefOr[Array[String] | PropOptions.Rules] =
    undefined
}

@ScalaJSDefined
class ComponentOptions[D, C](
  val data: UndefOr[js.Function0[D]] = undefined,
  val computed: UndefOr[C] = undefined
) extends GenOptions[Vue[D, C]]
     with ReactiveData[UndefOr[js.Function0[D]]]
     with ComputedProperty[UndefOr[C]]

object ComponentOptions {
  type LifecycleHook[V] = ThisFunction0[V, Unit]

  def apply[D, C](data: D, computed: UndefOr[C] = undefined)
                  : ComponentOptions[D, C] =
    new ComponentOptions(js.defined(() => data), computed)
}

@ScalaJSDefined
class PropOptions[A <% js.Any](
  @JSName("type")
  val constructor: UndefOr[Constructor[A]] = undefined,
  val required: UndefOr[Boolean] = undefined,
  val default: UndefOr[A] = undefined,
  val validator: UndefOr[js.Function1[A, Boolean]] = undefined
) extends Object

object PropOptions {
  type Rules = Dictionary[PropOptions[_]]
  def apply[A <% Any](constructor: UndefOr[Constructor[A]] = undefined,
                      required: UndefOr[Boolean] = undefined,
                      default: UndefOr[A] = undefined,
                      validator: UndefOr[js.Function1[A, Boolean]] = undefined)
                      : PropOptions[A] =
    new PropOptions(constructor, required, default, validator)

  def apply(rules: (String, PropOptions[_])): Rules =
    Dictionary(rules)
}

@native
trait Constructor[A] extends js.Function
object Constructor {
  val String = js.Dynamic.global.String.asInstanceOf[Constructor[String]]
  val Number = js.Dynamic.global.Number.asInstanceOf[Constructor[Double]]
  val Boolean = js.Dynamic.global.Boolean.asInstanceOf[Constructor[Boolean]]
  val Function = js.Dynamic.global.Function.asInstanceOf[Constructor[js.Function]]
  val Object = js.Dynamic.global.Object.asInstanceOf[Constructor[Object]]
  def Dictionary[A] = js.Dynamic.global.Object.asInstanceOf[Constructor[Dictionary[A]]]
  def Array[A] = js.Dynamic.global.Array.asInstanceOf[Constructor[Array[A]]]
  def forType[A <: Any: ConstructorTag] =
    js.constructorTag[A].constructor.asInstanceOf[Constructor[A]]
}

@ScalaJSDefined
class Accessor[V, A](
  val get: Computed.Getter[V, A],
  val set: Computed.Setter[V, A]
) extends Object

object Accessor {
  def apply[V, A](get: Computed.Getter[V, A], set: Computed.Setter[V, A])
                  : Accessor[V, A] = new Accessor(get, set)
}

object Computed {
  type Getter[V, A] = ThisFunction0[V, A]
  type Setter[V, A] = ThisFunction1[V, A, Unit]
}

@native
class Vue[D, C](options: ComponentOptions[D, C]) extends Object {
  import dom.html.Element

  type DataView = D with C
  type Sibling = Vue[Any, Any]

  @JSBracketAccess
  def apply(key: String): Any = native
  @JSBracketAccess
  def get[A](key: String): UndefOr[A] = native
  @JSBracketAccess
  def update(key: String, value: Any): Unit = native

  val $data: Dynamic = native
  val $el: Element = native
  val $options: ComponentOptions[D, C] = native
  val $parent: UndefOr[Sibling] = native
  val $root: Sibling = native
  val $children: Array[Sibling] = native
  val $refs: Dictionary[Object] = native
  val $slots: Dictionary[Array[VNode]] = native
  val $scopedSlots: Dictionary[js.Function1[Any, _]] = native
  val $isServer: Boolean = native

  val $props: Any = native

  def $mount(elementOrSelector: UndefOr[Element|String] = undefined,
            hydrating: UndefOr[Boolean] = undefined)
            : this.type = native

  def $forceUpdate(): Unit = native
  def $destroy(): Unit = native
  def $set[A](`object`: Object, key: String, value: A): A = native
  def $set[A](array: Array[A], key: Int, value: A): A = native
  def $delete(`object`: Object, key: String): Unit = native
  def $watch[T](expOrFn: String | ThisFunction0[this.type, T],
                callback: WatchHandler[this.type, T],
                options: UndefOr[WatchOptions])
                : js.Function0[Unit] = native
  def $on(event: String | Array[String], callback: js.Function): this.type = native
  def $once(event: String, callback: js.Function): this.type = native
  def $off(event: UndefOr[String | Array[String]] = undefined,
          callback: UndefOr[js.Function]): this.type = native

  def $emit(event: String, args: Array[Any]): this.type = native
  def $nextTick(callback: ThisFunction0[this.type, Unit]): Unit = native
  def $nextTick(): Promise[Unit] = native
  var $createElement: js.Function = native // FIXME give clearer type
}

@native
trait VueStatic extends Object {

  val config: VueConfig = native

  def extend[D, C, V <: Vue[D, C]](options: ComponentOptions[C, D]): VueConstructor[D, C, V] = native

  def set[A](`object`: Object, key: String, value: A): A = native
  def set[A](array: Array[A], key: Int, value: A): A = native
  def delete(`object`: Object, key: String): Unit = native

  def directive(id: String, definition: DirectiveOptions): DirectiveOptions = native
  def filter(id: String, definition: js.Function): js.Function = native
  def component[D, C, V <: Vue[D, C]](id: String, definition: UndefOr[ComponentOptions[D, C]]): VueConstructor[D, C, V] = native

  def use[T](plugin: PluginObject[T] | PluginFunction[T],
            options: UndefOr[T] = undefined): Unit = native

  def mixin(`mixin`: Any): Unit = native // TODO
  def compile(template: String): Template = native
}

@native
class Template extends Object {
  def render(createElement: js.Function): VNode = native
  var staticRenderFns: Array[js.Function1[Unit, VNode]] = native
}

@native
class VueConfig extends Object {
  var silent: Boolean = native
  val optionMergeStrategies: Dictionary[js.Function] = native
  var devtools: Boolean = native
  var errorHandler: js.Function2[js.Error, _, _] = native // TODO
  var ignoredElements: Array[String] = native
}
