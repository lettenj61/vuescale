// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

@ScalaJSDefined
abstract class Directive extends Object {

  type DirectiveFn = js.Function4[dom.html.Element, VNodeDirective, VNode, VNode, Unit]

  var bind: UndefOr[DirectiveFn] = undefined
  var inserted: UndefOr[DirectiveFn] = undefined
  var update: UndefOr[DirectiveFn] = undefined
  var componentUpdated: UndefOr[DirectiveFn] = undefined
  var unbind: UndefOr[DirectiveFn] = undefined
}
