package vuescale
package scaladsl

import scala.language.implicitConversions

import scala.scalajs.js
import org.scalajs.dom

import vuescale.facade.Vue

trait WrapperLowPriorityImplicits {
  sealed trait ElementOrSelector extends js.Object // TODO: move to outer package as native JS trait
  @inline implicit def StringSelector(selector: String): ElementOrSelector =
    selector.asInstanceOf[ElementOrSelector]

  @inline implicit def ElementElement(element: dom.Element): ElementOrSelector =
    element.asInstanceOf[ElementOrSelector]
}

abstract class BaseComponentWrapper
    extends WrapperLowPriorityImplicits {

  type Data <: js.Object
  type Props
  type ViewModel = Vue with Data

  trait DataOptions extends js.Object {
    def data(): Data
    def props: js.UndefOr[Props] = js.undefined
    val computed: ComputedProperty
    val methods: Methods
  }
  trait ComputedProperty extends js.Object
  trait Methods extends js.Object

  trait DOMOptions extends js.Object {
    def el: js.UndefOr[ElementOrSelector] = js.undefined
    def template: js.UndefOr[String] = js.undefined
  }
}

abstract class ComponentWrapper
    extends BaseComponentWrapper {

  abstract class ComponentOptions
      extends DataOptions
         with DOMOptions
         with LifecycleOptions {

    val name: String
    def functional: js.UndefOr[Boolean] = js.undefined
    override val methods: Methods = js.undefined.asInstanceOf[Methods]
  }
  type LifecycleHook = js.ThisFunction0[ViewModel, Unit]

  trait LifecycleOptions extends js.Object {
    def beforeCreate: js.UndefOr[LifecycleHook] = js.undefined
    def created: js.UndefOr[LifecycleHook] = js.undefined
    def beforeMount: js.UndefOr[LifecycleHook] = js.undefined
    def mounted: js.UndefOr[LifecycleHook] = js.undefined
    def beforeUpdate: js.UndefOr[LifecycleHook] = js.undefined
    def updated: js.UndefOr[LifecycleHook] = js.undefined
    def activated: js.UndefOr[LifecycleHook] = js.undefined
    def deactivated: js.UndefOr[LifecycleHook] = js.undefined
    def beforeDestroy: js.UndefOr[LifecycleHook] = js.undefined
    def destroyed: js.UndefOr[LifecycleHook] = js.undefined
  }

  protected[this] def withContext[A](self: js.Object)(f: ViewModel => A): A =
    f(ThisContext.resolve(self))

  def component: ComponentOptions
}