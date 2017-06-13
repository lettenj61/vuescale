package vuescale
package facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@JSGlobal @native
class Vue(options: Any) extends Object {
  import dom.html.Element

  @JSBracketAccess
  def apply(key: String): Any = native
  @JSBracketAccess
  def get[A](key: String): UndefOr[A] = native
  @JSBracketAccess
  def update(key: String, value: Any): Unit = native

  val $data: Dynamic = native
  val $el: Element = native
  val $options: Any = native
  val $parent: UndefOr[Vue] = native
  val $root: Vue = native
  val $children: Array[Vue] = native
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

  def extend(options: Any): js.Function = native

  def set[A](`object`: Object, key: String, value: A): A = native
  def set[A](array: Array[A], key: Int, value: A): A = native
  def delete(`object`: Object, key: String): Unit = native

  def directive(id: String, definition: DirectiveOptions): DirectiveOptions = native
  def filter(id: String, definition: js.Function): js.Function = native
  def component(id: String, definition: Any = undefined): js.Function = native

  def use[T](plugin: PluginObject[T] | PluginFunction[T],
            options: UndefOr[T] = undefined): Unit = native

  def mixin(`mixin`: Any): Unit = native // TODO
  def compile(template: String): Template = native
}

@native
trait Template extends Object {
  def render(createElement: js.Function): VNode = native
  var staticRenderFns: Array[js.Function1[Unit, VNode]] = native
}

@native
trait VueConfig extends Object {
  var silent: Boolean = native
  val optionMergeStrategies: Dictionary[js.Function] = native
  var devtools: Boolean = native
  var errorHandler: js.Function2[js.Error, _, _] = native // TODO
  var ignoredElements: Array[String] = native
}

@native
trait PluginObject[T] extends Object {
  var install: PluginFunction[T] = native

  @JSBracketAccess
  def get(key: String): Any = native
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
