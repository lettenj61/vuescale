package vuescale

import scala.scalajs.js
import org.scalajs.dom

package object facade {

  type WatchHandler[V, T] = js.ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type PluginFunction[T] = js.Function2[Vue, js.UndefOr[T], Unit]
}
