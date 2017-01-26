// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

// FIXME: stub
@native
trait ScopedSlot extends Object

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
  var isStatic: Boolean = native
  var isRootInsert: Boolean = native
  var isComment: Boolean = native
}

@native
trait VNodeComponentOptions extends Object {
  // FIXME this may be encoded with ScalaJSDefined
}

@native
trait VNodeData extends Object {
  var key: String | Double = native
  var slot: String = native
  var scopedSlots: Dictionary[ScopedSlot] = native
  var ref: String = native
  var tag: String = native
  var staticClass: String = native
  @JSName("class")
  var jsClass: Any = native // FIXME: could there be any good name?
  var staticStyle: Dictionary[Any] = native
  var style: Object = native // FIXME: is there any situation that it needs to be an Array?
  var props: Dictionary[Any] = native
  var attrs: Dictionary[Any] = native
  var domProps: Dictionary[Any] = native
  var hook: Dictionary[js.Function] = native
  var on: js.Dynamic = native // FIXME: give it a type
  var nativeOn: js.Dynamic = native // FIXME: give it a type
  var transition: Object = native
  var show: Boolean = native
  var inlineTemplate: InlineTemplate = native
  var directives: Array[VNodeDirective] = native
  var keepAlive: Boolean = native
}

@ScalaJSDefined
trait InlineTemplate extends Object {
  val render: js.Function
  val staticReaderFns: Array[js.Function]
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