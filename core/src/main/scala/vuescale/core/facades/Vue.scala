// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._

import org.scalajs.dom

// stub
@native
class Vue(options: ComponentOptions[Vue]) extends Object {

  val $data: Dynamic = native
  val $el: dom.html.Element = native
  val $options: ComponentOptions[Vue] = native
  val $parent: Vue = native
  val $root: Vue = native
  // TODO: `refs`
  val $refs: Dictionary[Object] = native
  val $slots: Dictionary[Array[VNode]] = native
  val $scopedSlots: Dictionary[ScopedSlot] = native
  val $isServer: Boolean = native

  def $mount(elementOrSelector: String = ???,
            hydrating: Boolean = ???)
            : this.type = native
}

@native
trait VueStatic extends Object {

  val config: VueConfig = native

  def extend(options: ComponentOptions[_]): Vue = native

  def set[A](`object`: Object, key: String, value: A): A = native
  def set[A](array: Array[A], key: Int, value: A): A = native
  def delete(`object`: Object, key: String): Unit = native

  def directive(id: String, definition: DirectiveOptions): DirectiveOptions =
    native
  def filter(id: String, definition: js.Function): js.Function = native
}

@native
class VueConfig extends Object {
  var silent: Boolean = native
  val optionMergeStrategies: Dictionary[js.Function] = native
  var devtools: Boolean = native
  var errorHandler: js.Function2[js.Error, Vue, _] = native
  var ignoredElements: Array[String] = native
}
