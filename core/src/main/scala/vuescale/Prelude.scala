package vuescale

import scala.{ Any => _, Array => _ }

import scala.scalajs.js
import js.annotation._

import vuescale.facades._

object prelude {

  type ComponentOptions[V <: Vue, I] = dsl.ComponentOptions[V, I]

  type Vue = facades.Vue
  lazy val Vue: VueStatic = js.Dynamic.global.Vue.asInstanceOf[VueStatic]
}

