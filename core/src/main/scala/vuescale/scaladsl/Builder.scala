package vuescale
package scaladsl

import scala.scalajs.js
import js.|

import org.scalajs.dom

/**
 * Base trait for component builders.
 */
trait Builder {
  def el: Builder.Elem
}

object Builder {
  type Elem = String | dom.Element
}

/**
 * Builder at the state which only data type is specified.
 */
class WithData[D](val el: Builder.Elem, val initData: D) extends Builder
