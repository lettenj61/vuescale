package vuescale
package facade

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation._

import org.scalajs.dom.Element

@JSImport("vue", JSImport.Default, globalFallback = "Vue")
@js.native
class Vue(options: js.Any) extends js.Object {

  @JSBracketAccess
  def apply(key: String): js.Any = js.native
  @JSBracketAccess
  def get[A](key: String): js.UndefOr[A] = js.native
  @JSBracketAccess
  def update(key: String, value: js.Any): Unit = js.native

  val $data: js.Dynamic = js.native
  val $el: Element = js.native
  val $options: js.Any = js.native
  val $parent: js.UndefOr[Vue] = js.native
  val $root: Vue = js.native
  val $children: js.Array[Vue] = js.native
  val $refs: js.Dictionary[js.Object] = js.native
  val $slots: js.Dictionary[js.Array[VNode]] = js.native
  val $scopedSlots: js.Dictionary[js.Function1[Any, _]] = js.native
  val $isServer: Boolean = js.native

  val $props: js.Any = js.native

  def $mount(
    elementOrSelector: js.UndefOr[Element|String] = js.undefined,
    hydrating: js.UndefOr[Boolean] = js.undefined
  ): this.type = js.native

  def $forceUpdate(): Unit = js.native
  def $destroy(): Unit = js.native
  def $set[A](`object`: js.Object, key: String, value: A): A = js.native
  def $set[A](array: js.Array[A], key: Int, value: A): A = js.native
  def $delete(`object`: js.Object, key: String): Unit = js.native

  def $watch[T](
    expOrFn: String | js.ThisFunction0[this.type, T],
    callback: WatchHandler[this.type, T],
    options: js.UndefOr[WatchOptions]
  ): js.Function0[Unit] = js.native

  def $on(event: String | js.Array[String], callback: js.Function): this.type = js.native
  def $once(event: String, callback: js.Function): this.type = js.native

  def $off(
    event: js.UndefOr[String | js.Array[String]] = js.undefined,
    callback: js.UndefOr[js.Function]
  ): this.type = js.native

  def $emit(event: String, args: js.Array[Any]): this.type = js.native
  def $nextTick(callback: js.ThisFunction0[this.type, Unit]): Unit = js.native
  def $nextTick(): js.Promise[Unit] = js.native
  var $createElement: js.Function = js.native // FIXME give clearer type
}

@js.native
trait VueStatic extends js.Object {

  val config: VueConfig = js.native

  def extend(options: js.Any): js.Function = js.native

  def set[A](`object`: js.Object, key: String, value: A): A = js.native
  def set[A](array: js.Array[A], key: Int, value: A): A = js.native
  def delete(`object`: js.Object, key: String): Unit = js.native

  def directive(id: String, definition: DirectiveOptions): DirectiveOptions = js.native
  def filter(id: String, definition: js.Function): js.Function = js.native
  def component(id: String, definition: js.Any = js.undefined): js.Function = js.native

  def use[T](
    plugin: PluginObject[T] | PluginFunction[T],
    options: js.UndefOr[T] = js.undefined
  ): Unit = js.native

  def mixin(`mixin`: js.Any): Unit = js.native // TODO
  def compile(template: String): Template = js.native
}

@JSImport("vue", JSImport.Default, globalFallback = "Vue")
@js.native
object Vue extends VueStatic

@js.native
trait Template extends js.Object {
  def render(createElement: js.Function): VNode = js.native
  var staticRenderFns: js.Array[js.Function1[Unit, VNode]] = js.native
}

@js.native
trait VueConfig extends js.Object {
  var silent: Boolean = js.native
  val optionMergeStrategies: js.Dictionary[js.Function] = js.native
  var devtools: Boolean = js.native
  var errorHandler: js.Function2[js.Error, _, _] = js.native // TODO
  var ignoredElements: js.Array[String] = js.native
  var keyCodes: js.Dictionary[Int | js.Array[Int]] = js.native // TODO
  var performance: Boolean = js.native
  var productionTip: Boolean = js.native
}

@js.native
trait PluginObject[T] extends js.Object {
  var install: PluginFunction[T] = js.native

  @JSBracketAccess
  def get(key: String): js.Any = js.native
}

@ScalaJSDefined
class RenderContext(
  val props: js.Any,
  val children: js.Array[VNode],
  val data: VNodeData,
  val parent: Vue
) extends js.Object {

  def slots(): js.Any = js.undefined
}

@ScalaJSDefined
trait WatchOptions extends js.Object {
  val deep: js.UndefOr[Boolean] = js.undefined
  val immediate: js.UndefOr[Boolean] = js.undefined
}

@ScalaJSDefined
trait DirectiveOptions extends js.Object {
  var bind: js.UndefOr[DirectiveFunction] = js.undefined
  var inserted: js.UndefOr[DirectiveFunction] = js.undefined
  var update: js.UndefOr[DirectiveFunction] = js.undefined
  var componentUpdated: js.UndefOr[DirectiveFunction] = js.undefined
  var unbind: js.UndefOr[DirectiveFunction] = js.undefined
}
