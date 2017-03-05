// Most types are port of Vue.js official type definitions for TypeScript
// https://github.com/vuejs/vue/tree/dev/types

package vuescale.core.facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

@native
trait PluginObject[T] extends Object {
  var install: PluginFunction[T] = native

  @JSBracketAccess
  def get(key: String): Any = native
}