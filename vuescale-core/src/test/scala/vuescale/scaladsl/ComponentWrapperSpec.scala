package vuescale
package scaladsl

import org.scalatest.FunSpec
import vuescale.facade.Vue

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{ global => g }

object Counter extends ComponentWrapper {
  class Data(var count: Int) extends js.Object
  trait Injected extends js.Object {
    def doubled: Int
  }
  def component: ComponentOptions = new ComponentOptions {
    val name: String = "counter"
    override def data(): Data = new Data(1)
    override val computed: ComputedProperty = new ComputedProperty {
      def doubled(): Int = withContext(this)(vm => {
        g.console.log("%o", vm)
        vm.count * 2
      })
    }
  }
  def apply(): ViewModel =
    component.asInstanceOf[ViewModel]
}

class ComponentWrapperSpec extends FunSpec {
  describe("ComponentWrapper") {
    it("generate component options") {
      val vm = new Vue(Counter()).asInstanceOf[Counter.ViewModel]

      assert(vm.count == 1)
      assert(vm.doubled == 2)
    }
  }
}
