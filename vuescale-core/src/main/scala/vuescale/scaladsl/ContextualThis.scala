package vuescale
package scaladsl

import scala.language.implicitConversions
import scala.scalajs.js

trait ContextualThis[A] extends js.Function0[A]
object ContextualThis {
  private[this] final val resolver: ContextualThis[js.Dynamic] =
    js.Function("return this;").asInstanceOf[ContextualThis[js.Dynamic]]

  private[scaladsl] def resolve[A](thisArg: js.Any): A =
    resolver.call(thisArg).asInstanceOf[A]
}
