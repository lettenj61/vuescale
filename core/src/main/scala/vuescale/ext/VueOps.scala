package vuescale.ext

import vuescale.facades.Vue

class VueOps(val self: Vue) extends AnyVal {
  def viewAs[Data]: Data = self.$data.asInstanceOf[Data]
}