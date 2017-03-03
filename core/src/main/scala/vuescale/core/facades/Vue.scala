// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._

import org.scalajs.dom
import dom.html.Element

// stub
@native
class Vue(options: ComponentOptions[Vue]) extends Object {

  val $data: Dynamic = native
  val $el: Element = native
  val $options: ComponentOptions[Vue] = native
  val $parent: Vue = native
  val $root: Vue = native
  val $children: Array[Vue] = native
  // TODO: `refs`
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

  def extend(options: ComponentOptions[_]): Vue = native

  def set[A](`object`: Object, key: String, value: A): A = native
  def set[A](array: Array[A], key: Int, value: A): A = native
  def delete(`object`: Object, key: String): Unit = native

  def directive(id: String, definition: DirectiveOptions): DirectiveOptions = native
  def filter(id: String, definition: js.Function): js.Function = native
  def component(id: String, definition: UndefOr[Component | AsyncComponent]): Vue = native

  def use[T](plugin: PluginObject[T] | PluginFunction[T],
            options: UndefOr[T] = undefined): Unit = native

  def mixin(`mixin`: Vue | ComponentOptions[Vue]): Unit = native
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
  var errorHandler: js.Function2[js.Error, Vue, _] = native
  var ignoredElements: Array[String] = native
}
