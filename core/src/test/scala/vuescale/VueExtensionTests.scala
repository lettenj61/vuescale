package vuescale

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import facades._, raw._
import test.SampleData

import js.annotation._

class VueExtensionTests extends FunSuite {

  test("Typed $data property") {
    val data = new SampleData
    val opts = ComponentOptions(data = data)
    val vm: Vue = new Vue(opts)
    val view = vm.proxy.asInstanceOf[SampleData]
    assert(view.count === 777)

    data.count = 45
    assert(vm("count") === 45)
  }
}