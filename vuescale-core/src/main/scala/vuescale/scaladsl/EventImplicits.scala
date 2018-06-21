package vuescale
package scaladsl

import org.scalajs.dom

object EventImplicits {
  implicit class RichEvent(val event: dom.Event) extends AnyVal {
    def inputValue: String =
      event.target.asInstanceOf[dom.html.Input].value
  }
}