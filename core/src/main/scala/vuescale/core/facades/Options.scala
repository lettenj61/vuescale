// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
abstract class ComponentOptions[Data] extends Object {

  type V = Vue[Data]

  type LifecycleHook = UndefOr[ThisFunction0[V, Unit]]

  val template: UndefOr[String] = undefined
  var data: UndefOr[Data] = undefined

  var computed: UndefOr[Dictionary[ThisFunction0[V, _]]] = undefined
  var methods: UndefOr[Dictionary[ThisFunction1[V, Array[_], _]]] = undefined

  var beforeCreate: LifecycleHook = undefined
  var created: LifecycleHook = undefined
  var beforeDestroy: LifecycleHook = undefined
  var destroyed: LifecycleHook = undefined
  var beforeMount: LifecycleHook = undefined
  var mounted: LifecycleHook = undefined
  var beforeUpdate: LifecycleHook = undefined
  var updated: LifecycleHook = undefined
  var activated: LifecycleHook = undefined
  var deactivated: LifecycleHook = undefined

  var parent: UndefOr[Vue[_]] = undefined
  // TODO: `mixins`
  var name: UndefOr[String] = undefined
  // TODO: `extends`
  var delimiters: UndefOr[(String, String)] = undefined
}

@ScalaJSDefined
abstract class ComponentFunction[D] extends ComponentOptions[js.Function0[D]]
