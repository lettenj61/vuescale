package vuescale.ext

import vuescale.facades.Vue

class VueOps(val self: Vue) extends AnyVal {

  def getOrElse[A](key: String, default: => A): A =
    self.get[A](key).getOrElse(default)

  def viewAs[Data]: Data = self.$data.asInstanceOf[Data]
}