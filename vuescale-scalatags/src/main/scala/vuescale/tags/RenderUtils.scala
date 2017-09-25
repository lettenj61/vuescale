package vuescale.tags

import scala.scalajs.js
import scalatags.generic

/** Helpers to manipulate JavaScript object with Scalatags' [[Modifier]]s.
  */
trait RenderUtils {

  def styleMap[B0](styles: generic.StylePair[B0, _]*): js.Dictionary[String] =
    js.Dictionary(styles.map(s => (s.s.jsName, s.v.toString)): _*)
}

object RenderUtils extends RenderUtils
