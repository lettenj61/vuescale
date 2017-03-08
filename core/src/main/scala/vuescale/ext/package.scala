package vuescale

package object ext {
  import facades._
  import scala.language.implicitConversions

  implicit def vueOps(v: Vue): VueOps = new VueOps(v)
}