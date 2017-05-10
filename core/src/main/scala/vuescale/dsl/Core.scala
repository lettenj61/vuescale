package vuescale
package dsl

import scala.{ Any => _, Array => _ }

import scala.scalajs.js
import js.annotation._

import vuescale.facades._

trait Boilerplate {
  import js._

  def wrapThisFn0[T, R](f: T => R): ThisFunction0[T, R] =
    (self: T) => f(self)
  def wrapThisFn1[T, A, R](f: (T, A) => R): ThisFunction1[T, A, R] =
    (self: T, a: A) => f(self, a)
  def wrapThisFn2[T,A,B,R](f: (T,A,B) => R): ThisFunction2[T,A,B,R] =
    (self: T, a: A, b: B) => f(self, a, b)
  def wrapThisFn3[T,A,B,C,R](f: (T,A,B,C) => R): ThisFunction3[T,A,B,C,R] =
    (self: T, a: A, b: B, c: C) => f(self, a, b, c)
}

class ComponentOptions[V <: Vue, I](
  val el: js.UndefOr[String] = js.undefined,
  val initData: js.UndefOr[I] = js.undefined,
  val template: js.UndefOr[String] = js.undefined,
  val props: js.UndefOr[js.Array[String]] = js.undefined,
) {

  def build(): js.Dynamic = js.Dynamic.literal(
    el = el,
    data = () => initData,
    template = template,
    props = props
  )
}
