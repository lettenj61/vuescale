package vuescale
package facades

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

package object raw {

  type Vue = generic.Vue[Any, Any]
  type ComponentOptions = generic.ComponentOptions[Any, Any]
  def ComponentOptions(data: Any = undefined, computed: Any = undefined): ComponentOptions =
    generic.ComponentOptions[Any, Any](data, computed)
}
