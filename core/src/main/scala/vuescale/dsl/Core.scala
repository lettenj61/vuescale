package vuescale
package dsl

import scala.scalajs.js
import js.Dynamic.literal

import vuescale.prelude._

trait ThisFnBoilerplates[Repr <: js.Object, Data] {

  protected[this] type Context = Repr with Data

  protected object Bind {

    def apply[R](f: Context => R): js.ThisFunction0[Context, R] = f
    def apply[A,R](f: (Context, A) => R): js.ThisFunction1[Context, A, R] = f
    def apply[A,B,R](f: (Context,A,B) => R): js.ThisFunction2[Context,A,B,R] = f
    def apply[A,B,C,R](f: (Context,A,B,C) => R): js.ThisFunction3[Context,A,B,C,R] = f
    def apply[A,B,C,D,R](f: (Context,A,B,C,D) => R): js.ThisFunction4[Context,A,B,C,D,R] = f
    def apply[A,B,C,D,E,R](f: (Context,A,B,C,D,E) => R): js.ThisFunction5[Context,A,B,C,D,E,R] = f
    def apply[A,B,C,D,E,F,R](f: (Context,A,B,C,D,E,F) => R): js.ThisFunction6[Context,A,B,C,D,E,F,R] = f
    def apply[A,B,C,D,E,F,G,R](f: (Context,A,B,C,D,E,F,G) => R): js.ThisFunction7[Context,A,B,C,D,E,F,G,R] = f
  }
}

abstract class ComponentBuilder[V <: Vue, I](name: String, initData: I)
    extends ThisFnBoilerplates[V, I] {

  protected[this] val computed: js.Dynamic = literal()
  protected[this] val methods: js.Dynamic = literal()

  def build: js.Dynamic = literal(
    el = name,
    data = () => initData,
    computed = computed,
    methods = methods
  )
}

