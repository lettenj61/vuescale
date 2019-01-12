package vuescale
package scaladsl

import org.scalatest.FunSpec
import vuescale.facade.Vue

import scala.scalajs.js
import scala.scalajs.js.UndefOr

object Counter extends ComponentWrapper {
  class Data(var count: Int) extends js.Object
  def build = { implicit self =>
    new ComponentOptions {
      override def name: String = "counter"
      override def functional: UndefOr[Boolean] = false
      override def data(): Data = new Data(0)

      override def computed: UndefOr[Counter.ComputedProperty] = new ComputedProperty {
        def doubled(): Int = vm.count * 2
      }
    }
  }

  def apply(): ViewModel =
    build(null.asInstanceOf[Self]).asInstanceOf[ViewModel]
}

class ComponentWrapperSpec extends FunSpec {
  describe("ComponentWrapper") {
    it("generate component options") {
      val vm = new Vue(Counter()).asInstanceOf[Counter.ViewModel]

      assert(vm.count == 0)
      // assert(vm.doubled)
    }
  }
}
