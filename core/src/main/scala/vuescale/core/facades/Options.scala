// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
abstract class ComponentOptions[V <: Vue] extends Object {
  var data: Object
  // TODO: fill things!
}
