package vuescale.core

import scala.{ Array => _, Any => _ }
import scala.language.implicitConversions
import scala.scalajs.js
import js._

import org.scalajs.dom

package object facades {

  // implicit def StringVNode(s: String): VNode = s.asInstanceOf[VNode]
  type Constructor = js.Function1[Array[Any], Any]
  type Component = Vue | ComponentOptions[Vue]

  type WatchHandler[V, T] = ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type VNodeList = Array[VNode]
/*
  type Functions = js.Array[js.Function]

  implicit def function2Functions(f: js.Function): Functions = js.Array(f)
*/
}