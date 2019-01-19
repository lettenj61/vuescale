package vuescale
package scaladsl

import scala.language.implicitConversions

import scala.scalajs.js
import org.scalajs.dom

import vuescale.facade.Vue
import vuescale.scaladsl.Observed.ObservedOps

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
  // type Props
  type Computed <: js.Object
  type Methods <: js.Object
  type ViewModel = Vue with Data with Computed with Methods

  // TODO: Integrate these inner classes with non-native option objects used in Builder API

  trait DataOptions extends js.Object {
    def data(): Data
    def props: js.UndefOr[js.Object] = js.undefined // FIXME
    val computed: Computed
    val methods: Methods
  }

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
    override val computed: Computed = js.undefined.asInstanceOf[Computed]
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

  @inline protected[this] implicit def observeComputed(
    observed: Computed
  ): ObservedOps[Computed, ViewModel] =
    new ObservedOps[Computed, ViewModel](observed)

  @inline protected[this] implicit def observeMethods(
    observed: Methods
  ): ObservedOps[Methods, ViewModel] =
    new ObservedOps[Methods, ViewModel](observed)

  def component: ComponentOptions
}