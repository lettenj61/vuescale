package vuescale

import scala.{ Array => _, Any => _ }
import scala.language.implicitConversions
import scala.scalajs.js
import js._

import org.scalajs.dom

package object facades {

  val Vue: VueStatic = js.Dynamic.global.Vue.asInstanceOf[VueStatic]

  // implicit def StringVNode(s: String): VNode = s.asInstanceOf[VNode]
  type Constructor = js.Function1[Array[Any], Any]
  type Component = Vue | ComponentOptions[Vue] | ComponentOptionsFn

  private[facades] type Resolve = js.Function1[Component, Unit]
  private[facades] type Reject = js.Function1[Any, Unit]
  type AsyncComponent = js.Function2[Resolve, Reject, Promise[Component]]

  object CreateElement {
    type Empty = js.Function0[VNode]
    type Name = js.Function2[String, Any, VNode]
    type NameWithData = js.Function3[String, VNodeData, Any, VNode]
    type Constructor = js.Function2[Component, Any, VNode]
    type ConstructorWithData = js.Function3[Component, VNodeData, Any, VNode]
    type Async = js.Function2[AsyncComponent, Any, VNode]
    type AsyncWithData = js.Function3[AsyncComponent, VNodeData, Any, VNode]

    type All = Empty | Name | NameWithData | Constructor | ConstructorWithData | Async | AsyncWithData
  }
  type CreateElement = CreateElement.All

  type WatchHandler[V, T] = ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type PluginFunction[T] = js.Function2[Vue, UndefOr[T], Unit]

  type VNodeList = Array[VNode]
  type VueConstructor[V <: Vue] = js.Function1[UndefOr[ComponentOptions[V]], V]
}