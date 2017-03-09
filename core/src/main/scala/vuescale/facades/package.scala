package vuescale

import scala.{ Array => _, Any => _ }
import scala.language.implicitConversions
import scala.scalajs.js
import js._

import org.scalajs.dom

package object facades {
  import generic.{ ComponentOptions, Vue, VueStatic }

  val Vue: VueStatic = js.Dynamic.global.Vue.asInstanceOf[VueStatic]

  type WatchHandler[V, T] = ThisFunction2[V, T, T, Unit]
  type DirectiveFunction = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  type PluginFunction[T] = js.Function2[Vue[_, _], UndefOr[T], Unit]

  type VueConstructor[D, C, V <: Vue[D, C]] = js.Function1[UndefOr[ComponentOptions[D, C]], V]

  class VueOps[D, C](val self: Vue[D, C]) extends AnyVal {
    def data: D = self.asInstanceOf[D]
    def computed: C = self.asInstanceOf[C]
    def proxy: self.DataView = self.asInstanceOf[self.DataView]

    def getOrElse[A](key: String, default: => A): A =
      self.get[A](key).getOrElse(default)
  }

  implicit def vueOps[D, C](v: Vue[D, C]): VueOps[D, C] =
    new VueOps(v)

  def ComponentData[A](value: A): UndefOr[js.Function0[A]] = js.defined { () => value }
  def Bindings[A](bindings: (String, A)*): UndefOr[js.Function0[Dictionary[A]]] =
    ComponentData(Dictionary(bindings: _*))
}