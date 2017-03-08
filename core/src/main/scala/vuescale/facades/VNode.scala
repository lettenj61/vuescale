// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

// FIXME they are not natives, but interfaces?
@native
trait VNode extends Object {
  var tag: String = native
  var data: VNodeData = native
  var children: Array[VNode] = native
  var text: String = native
  var elm: dom.Node = native
  var ns: String = native
  var componentOptions: VNodeComponentOptions = native
  var componentInstance: Vue = native
  var parent: VNode = native
  var raw: Boolean = native
  var isStatic: UndefOr[Boolean] = native
  var isRootInsert: Boolean = native
  var isComment: Boolean = native
}

@ScalaJSDefined
trait VNodeComponentOptions extends Object {
  val Ctor: Vue
  val propsData: UndefOr[Object] = undefined
  val listeners: UndefOr[Object] = undefined
  val children: UndefOr[Any] = undefined // FIXME: give correct type
  val tag: UndefOr[String] = undefined
}

@native
trait VNodeData extends Object {

  var key: UndefOr[String | Double] = native
  var slot: UndefOr[String] = native
  var scopedSlots: UndefOr[Dictionary[js.Function1[Any, _]]] = native
  var ref: UndefOr[String] = native
  var tag: UndefOr[String] = native
  var staticClass: UndefOr[String] = native
  @JSName("class")
  var jsClass: UndefOr[Any] = native // FIXME: could there be any good name?
  var staticStyle: UndefOr[Dictionary[Any]] = native
  var style: UndefOr[Object] = native // FIXME: is there any situation that it needs to be an Array?
  var props: UndefOr[Dictionary[Any]] = native
  var attrs: UndefOr[Dictionary[Any]] = native
  var domProps: UndefOr[Dictionary[Any]] = native
  var hook: UndefOr[Dictionary[js.Function]] = native
  var on: UndefOr[Dictionary[Any]] = native // FIXME: give its values a type
  var nativeOn: UndefOr[Dictionary[Any]] = native // FIXME: give its values a type
  var transition: UndefOr[Object] = native
  var show: UndefOr[Boolean] = native
  var inlineTemplate: UndefOr[InlineTemplate] = native
  var directives: UndefOr[Array[VNodeDirective]] = native
  var keepAlive: UndefOr[Boolean] = native
}

@ScalaJSDefined
trait InlineTemplate extends Object {
  val render: js.Function
  val staticRenderFns: Array[js.Function]
}

@ScalaJSDefined
trait VNodeDirective extends Object {
  val name: String
  val value: Any
  val oldValue: Any
  val expression: Any
  val arg: String
  val modifiers: Dictionary[Boolean]
}
