package vuescale

import scala.scalajs.js

import org.scalajs.dom

package object facade {

  type WatchHandler[V, T] = js.ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type PluginFunction[T] = js.Function2[VueStatic, js.UndefOr[T], Unit]
  type RenderFunction = js.Function1[CreateElement, VNode]
  type RenderThisFunction[T] = js.ThisFunction1[T, CreateElement, VNode]
}
