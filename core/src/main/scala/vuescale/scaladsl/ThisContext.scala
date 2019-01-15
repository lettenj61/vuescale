package vuescale
package scaladsl

import scala.language.implicitConversions
import scala.scalajs.js

@js.native
trait ThisContext[A] extends js.Function0[A]
object ThisContext {
  private[this] final val resolver: ThisContext[js.Dynamic] =
    js.Function("return this;").asInstanceOf[ThisContext[js.Dynamic]]

  private[scaladsl] def resolve[A](thisArg:js.Any): A =
    resolver.call(thisArg).asInstanceOf[A]
}
