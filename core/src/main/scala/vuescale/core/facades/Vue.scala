// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._

import org.scalajs.dom

// stub
@native
class Vue[Data](options: ComponentOptions[Data]) extends Object {

  val $data: Data = native
  val $el: dom.html.Element = native
  val $options: ComponentOptions[Data] = native
  val $parent: Vue[_] = native
  val $root: Vue[_] = native
  // TODO: `refs`
  val $refs: Dictionary[Object] = native
  val $slots: Dictionary[Array[VNode]] = native
  val $scopedSlots: Dictionary[ScopedSlot] = native
  val $isServer: Boolean = native

  def $mount(elementOrSelector: String = ???, hydrating: Boolean = ???): this.type = native
}

@native
trait VueStatic extends Object {

  val config: VueConfig = native

  def extend[D](options: ComponentFunction[D]): Vue[D] = native

  def set[A](`object`: Object, key: String, value: A): A = native
  def set[A](array: Array[A], key: Int, value: A): A = native
  def delete(`object`: Object, key: String): Unit = native

  def directive(id: String, definition: Directive): Directive = native
  def filter(id: String, definition: js.Function): js.Function = native
}

@native
class VueConfig extends Object {
  var silent: Boolean = native
  val optionMergeStrategies: Dictionary[js.Function] = native
  var devtools: Boolean = native
  var errorHandler: js.Function2[js.Error, Vue[_], _] = native
  var ignoredElements: Array[String] = native
}
