package vuescale

import scala.scalajs.js
import org.scalajs.dom

import facades._

trait Helpers {

  def vue(id: Option[String])(bindings: (String, js.Any)*): Vue = {
    val d = js.Dictionary(bindings: _*)
    val opts = id match {
      case Some(s) => new ComponentOptions[Vue] {
        override val el = js.defined(s)
        override val data = { () => d }
      }
      case None => new ComponentOptions[Vue] {
        override val data = { () => d }
      }
    }
    new Vue(opts)
  }

  def vueWithoutId(bindings: (String, js.Any)*): Vue = vue(None)(bindings: _*)
}
