package vuescale

import scala.scalajs.js

import vuescale.facade._

object prelude {

  type Vue = facade.Vue
  lazy val Vue: facade.Vue.type = facade.Vue

  type Handler[V <: js.Object] = scaladsl.Handler[V]

  type Component[V <: Vue] = scaladsl.Component[V]
  val Component = scaladsl.Component
}
