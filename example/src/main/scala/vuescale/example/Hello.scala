package vuescale.example

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.scalajs.dom
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.window.console

import vuescale.prelude._
import vuescale.tags.syntax._

@JSExportAll
case class Todo(id: Int, name: String, var done: Boolean)

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  class TodoList extends js.Object {
    var title = "Hello, Vue.js"
    var input = ""
    var todos: js.Array[Todo] = js.Array(
      Todo(0, "Learn Vue.js", true),
      Todo(1, "Learn Scala", true),
      Todo(2, "Write something awesome", false)
    )
  }

  type TodoListView = Vue with TodoList

  val TodoFooter = Component.builder("todo-footer")
    .template("<p>{{ message }}</p>")
    .data("message" -> "Enter your todos")
    .build

  val TodoMain = Component.builder[TodoListView]("todo-main")
    .data(new TodoList)
    .renderTags { (vm: TodoListView) =>
      <.div(
        <.h3(vm.title),
        <.input(
          ^.typeName := "text",
          ^.onKeyUp := { (e: dom.KeyboardEvent) =>
            if (e.keyCode == KeyCode.Enter) {
              vm.todos.push(Todo(
                vm.todos.length,
                vm.input,
                false
              ))
              vm.input = ""
              e.target.asInstanceOf[dom.html.Input].value = ""
            }
          },
          ^.onInput := { (e: dom.Event) =>
            console.log("%o", vm)
            console.log("%o", e)
            vm.input = e.inputValue
          }
        ),
        <.div((vm.todos.map(todo => <.li(todo.name)): _*))
      )
    }
    .build

  @JSExport
  def main(): Unit = {
    val vm = new Vue(Component(
      el = "#app",
      components = Seq("todo-main" -> TodoMain)
    ))

    console.log("%o", vm.$options)
  //   val vm: Vue = new Vue(Component(
  //     el = "#app",
  //     data = new TodoList,
  //     methods = new Handler[TodoListView] {
  //       val addTodo: Callback = { vm =>
  //         vm.todos.push(Todo(
  //           vm.todos.length,
  //           vm.input,
  //           false
  //         ))
  //         vm.input = ""
  //       }
  //       def checkTodo(todo: Todo): Unit = {
  //         todo.done = !todo.done
  //       }
  //     },
  //     components = Seq("todo-footer" -> TodoFooter)
  //   ))
  }
}
