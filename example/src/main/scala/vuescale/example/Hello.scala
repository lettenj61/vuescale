package vuescale.example

import scala.scalajs.js
import scala.scalajs.js.annotation._

import vuescale.prelude._

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

  @JSExport
  def main(): Unit = {
    val vm: Vue = new Vue(Component(
      el = "#app",
      data = new TodoList,
      methods = new Handler[TodoListView] {
        val addTodo: Callback = { vm =>
          vm.todos.push(Todo(
            vm.todos.length,
            vm.input,
            false
          ))
          vm.input = ""
        }
        def checkTodo(todo: Todo): Unit = {
          todo.done = !todo.done
        }
      },
      components = Seq("todo-footer" -> TodoFooter)
    ))
  }
}
