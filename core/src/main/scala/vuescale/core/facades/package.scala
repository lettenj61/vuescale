package vuescale.core

import scala.language.implicitConversions
import scala.scalajs.js

package object facades {

  implicit def StringVNode(s: String): VNode = ???

/*
  type Functions = js.Array[js.Function]

  implicit def function2Functions(f: js.Function): Functions = js.Array(f)
*/
}