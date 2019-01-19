package vuescale
package scaladsl

object Observed {
  /**
    * An `Observed` is a type which can be treated as
    * its type parameter `V`.
    *
    * It is used to infer `this` context inside Vue component definition.
    *
    * This class provides `asVue` to unsafely cast observed instance
    * to a type of its runtime representation.
    *
    * @tparam V   a type it would be resolved to at runtime.
    */
  class ObservedOps[S, V](val observed: S) extends AnyVal {
    @inline final def asVue: V = observed.asInstanceOf[V]
  }
}
