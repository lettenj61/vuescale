package vuescale

import scala.{ Any => _, Array => _ }

import scala.scalajs.js
import js.annotation._

import vuescale.facades._

object prelude {

  type ComponentBuilder[I] = ext.ComponentBuilder[Vue, I]

  type Vue = facades.Vue
  lazy val Vue: VueStatic = js.Dynamic.global.Vue.asInstanceOf[VueStatic]
}

