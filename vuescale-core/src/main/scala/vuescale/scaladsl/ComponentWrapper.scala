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

  trait ReactiveData extends js.Object {
    def data(): Data
    def props: js.UndefOr[Props] = js.undefined
  }
  trait InjectedProperties extends js.Object {
    def computed: js.UndefOr[ComputedProperty] = js.undefined
    def methods: js.UndefOr[Methods] = js.undefined
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

  trait ComponentOptions
      extends ReactiveData
        with InjectedProperties
        with DOMOptions
        with LifecycleOptions {

    def name: String
    def functional: js.UndefOr[Boolean]
  }

  type ViewModel = Vue with Data with ComponentOptions
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

  // stub
  protected[this] sealed trait Self extends js.Object
  @inline protected[this] def vm(implicit `this`: Self): ViewModel =
    ContextualThis.resolve[ViewModel](`this`)

  def build: Self => ReactiveData
}