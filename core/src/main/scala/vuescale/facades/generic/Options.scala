package vuescale.facades.generic

import scala.{ Array => _, Any => _ }
import scala.scalajs.js
import js._
import js.annotation._

import org.scalajs.dom

/** Typed Vue.js component.
  *
  * @tparam D   data type
  * @tparam C   computed property type
  * @tparam M   method provider type
  * @tparam V   view representation type
  */
@ScalaJSDefined
trait ComponentOptions[D, C, M, V] extends Object {
  def data: D
  def computed: C
  def methods: M
}

@ScalaJSDefined
class Accessor[V, A](
  val get: Computed.Getter[V, A],
  val set: Computed.Setter[V, A]
) extends Object

object Accessor {
  def apply[V, A](get: Computed.Getter[V, A], set: Computed.Setter[V, A])
                  : Accessor[V, A] = new Accessor(get, set)
}

object Computed {
  type Getter[V, A] = ThisFunction0[V, A]
  type Setter[V, A] = ThisFunction1[V, A, Unit]
}
