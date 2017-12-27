package vuescale
package tags

import scala.scalajs.js

import vuescale.facade.CreateElement
import vuescale.facade.VNode

/** DSL to write Vue.js VNode descriptors. The API design is heavily
 *  inspired by Scalatags ones.
 */
sealed trait VFragment {
  /** Serialize this fragment into VNode descriptors
   */
  def asDescriptor: js.Object // TODO: give descriptor a type

  /** Generate VNode by applying this fragment with
   *  given [[CreateElement]] function.
   */
  def renderWith(createElment: CreateElement): VNode
}