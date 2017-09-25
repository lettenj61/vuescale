// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale
package facade

import scala.scalajs.js
import js.|
import js.annotation.JSName

import org.scalajs.dom

@js.native
trait VNode extends js.Object {
  var tag: String = js.native
  var data: VNodeData = js.native
  var children: js.Array[VNode] = js.native
  var text: String = js.native
  var elm: dom.Node = js.native
  var ns: String = js.native
  var componentOptions: VNodeComponentOptions = js.native
  var componentInstance: Vue = js.native
  var parent: VNode = js.native
  var raw: Boolean = js.native
  var isStatic: js.UndefOr[Boolean] = js.native
  var isRootInsert: Boolean = js.native
  var isComment: Boolean = js.native
}

@js.native
trait VNodeComponentOptions extends js.Object {
  val Ctor: Vue = js.native
  val propsData: js.UndefOr[Object] = js.native
  val listeners: js.UndefOr[Object] = js.native
  val children: js.UndefOr[Any] = js.native // FIXME: give correct type
  val tag: js.UndefOr[String] = js.native
}

@js.native
trait VNodeData extends js.Object {

  var key: js.UndefOr[String | Double] = js.native
  var slot: js.UndefOr[String] = js.native
  var scopedSlots: js.UndefOr[js.Dictionary[js.Function1[js.Any, _]]] = js.native
  var ref: js.UndefOr[String] = js.native
  var tag: js.UndefOr[String] = js.native
  var staticClass: js.UndefOr[String] = js.native
  @JSName("class")
  var jsClass: js.UndefOr[js.Any] = js.native // FIXME: could there be any good name?
  var staticStyle: js.UndefOr[js.Dictionary[js.Any]] = js.native
  var style: js.UndefOr[js.Object] = js.native // FIXME: is there any situation that it needs to be an Array?
  var props: js.UndefOr[js.Dictionary[js.Any]] = js.native
  var attrs: js.UndefOr[js.Dictionary[js.Any]] = js.native
  var domProps: js.UndefOr[js.Dictionary[js.Any]] = js.native
  var hook: js.UndefOr[js.Dictionary[js.Function]] = js.native
  var on: js.UndefOr[js.Dictionary[js.Any]] = js.native // FIXME: give its values a type
  var nativeOn: js.UndefOr[js.Dictionary[js.Any]] = js.native // FIXME: give its values a type
  var transition: js.UndefOr[js.Object] = js.native
  var show: js.UndefOr[Boolean] = js.native
  var inlineTemplate: js.UndefOr[InlineTemplate] = js.native
  var directives: js.UndefOr[js.Array[VNodeDirective]] = js.native
  var keepAlive: js.UndefOr[Boolean] = js.native
}

@js.native
trait InlineTemplate extends js.Object {
  val render: js.Function = js.native
  val staticRenderFns: js.Array[js.Function] = js.native
}

@js.native
trait VNodeDirective extends js.Object {
  val name: String = js.native
  val value: js.Any = js.native
  val oldValue: js.Any = js.native
  val expression: js.Any = js.native
  val arg: String = js.native
  val modifiers: js.Dictionary[Boolean] = js.native
}
