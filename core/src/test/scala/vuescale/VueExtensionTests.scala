package vuescale

import scala.scalajs.js
import org.scalajs.dom
import org.scalatest._

import facades._
import ext._
import test.SampleData

import js.annotation._

class VueExtensionTests extends FunSuite with Helpers {

  test("Typed $data property") {
    val opts = new ComponentOptions[Vue] {
      val data = { () => new SampleData }
    }
    val vm: Vue = new Vue(opts)
    val data: SampleData = vm.viewAs[SampleData]
    assert(data.count === 777)

    data.count = 45
    assert(vm("count") === 45)
  }
}