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
abstract class Accessor[V, A] extends Object {

  def get(view: V): A
  def set(view: V, value: A): Unit

  @JSName("get")
  val getter: ThisFunction0[V, A] = this.get _
  @JSName("set")
  val setter: ThisFunction1[V, A, Unit] = this.set _
}
