package vuescale.example

import scala.scalajs.js

import scalatags.JsDom.all._
import vuescale.tags.Dom.all._

object Components {

  val template = tag("template")

  def Home = div(id := "app")(
    h3("{{ greet }}"),
    ul(vIf := "treasures.length > 1")(
      li(vFor := "t in treasures", "{{ t.name }}")
    )
  )
}
