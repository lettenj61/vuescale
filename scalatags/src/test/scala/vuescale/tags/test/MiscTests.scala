import scalatags.Text
import vuescale.tags._

import utest._

object MiscTests extends TestSuite {

  import Text.all._
  import Text.styles2._

  val tests = this {

    "styleObject" - {
      val styles = RenderUtils.styleMap(
        backgroundColor := "red",
        borderCollapse.collapse,
        textDecoration.underline
      )
      assert(
        styles("backgroundColor") == "red",
        styles("borderCollapse") == "collapse",
        styles("textDecoration") == "underline"
      )
    }
  }
}
