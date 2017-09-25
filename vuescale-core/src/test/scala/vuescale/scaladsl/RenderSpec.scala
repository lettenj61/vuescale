package vuescale

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.scalatest.FunSpec

import vuescale.prelude._

class RenderSpec extends FunSpec {

  @ScalaJSDefined
  class Task(val id: Int, val name: String) extends js.Object

  @ScalaJSDefined
  trait TaskList extends js.Object {
    val items: js.Array[Task]
  }

  type TaskView = Vue with TaskList

  describe("Options render") {

    it("basic usage") {
      val vm = new Vue(
        Component.builder
          .render({ (self: TaskList, h: CreateElement) =>
            val children = js.Array[VNode]()
            for (i <- 0 to self.items.length) {
              children += h(
                "li",
                js.Dictionary("staticClass" -> "task"),
                js.Array(self.items(i).name)
              )
              h("ul", js.Dictionary("staticClass" -> "tasks"), children)
            }
          })
          .data(new TaskList {
            val items = js.Array(
              new Task(1, "task1"),
              new Task(2, "task2")
            )
          })
          .result
      ).$mount().asInstanceOf[TaskView]

      assert(vm.$el.tagName == "UL")
      for (i <- 0 to vm.$el.children.length) {
        val li = vm.$el.children(i)
        assert(li.tagName == "LI")
        assert(li.textContent == vm.items(i).name)
      }
    }

  }
}