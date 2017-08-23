package vuescale
package scaladsl

import scala.scalajs.js
import js.|
import js.Dynamic.literal
import js.annotation.ScalaJSDefined
import org.scalajs.dom

import vuescale.facade._

/**
 * A helper singleton to create Vue instance and components.
 */
trait VueModel {

  // TODO: get it typed
  def withData[D <% js.Any](el: String, data: => D): Vue = {
    val options = ComponentOptions(el, data)
    new Vue(options)
  }
}

@ScalaJSDefined
trait ComponentOptions[D] extends js.Object {
  def el: js.UndefOr[String | dom.Element]
  def data: js.UndefOr[D]
}

@ScalaJSDefined
trait MethodProvider[D, M] extends ComponentOptions[D] {
  def methods: js.UndefOr[M]
}

object ComponentOptions {

  def apply[D <% js.Any](el: String | dom.Element, data: D): ComponentOptions[D] =
    literal(
      el = el.asInstanceOf[js.Dynamic],
      data = { () => data }
    ).asInstanceOf[ComponentOptions[D]]

  def apply[D <% js.Any, M <: js.Object](
    el: String | dom.Element,
    data: => D,
    methods: => M
  ): MethodProvider[D, M] = literal(
    el = el.asInstanceOf[js.Dynamic],
    data = { () => data },
    methods = methods
  ).asInstanceOf[MethodProvider[D, M]]
}
