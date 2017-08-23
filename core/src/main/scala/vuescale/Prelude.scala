package vuescale

import scala.scalajs.js

import vuescale.facade._
import vuescale.scaladsl

object prelude {

  type Vue = facade.Vue
  lazy val Vue: VueStatic = js.Dynamic.global.Vue.asInstanceOf[VueStatic]

  type VueModel = scaladsl.VueModel
  object VueModel extends scaladsl.VueModel

  type ComponentOptions[A] = scaladsl.ComponentOptions[A]
  val ComponentOptions = scaladsl.ComponentOptions
}

