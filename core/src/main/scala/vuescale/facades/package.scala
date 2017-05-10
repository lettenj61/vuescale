package vuescale

import scala.{ Array => _, Any => _ }
import scala.language.implicitConversions
import scala.scalajs.js
import js._

import org.scalajs.dom

package object facades {

  type WatchHandler[V, T] = ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type PluginFunction[T] = js.Function2[Vue, UndefOr[T], Unit]
}
