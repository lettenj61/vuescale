package vuescale.example

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.scalajs.dom
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.window.console

import vuescale.prelude._
import vuescale.tags.syntax._
import vuescale.tags.syntax.prefixed._

@JSExportAll
case class Todo(id: Int, name: String, var done: Boolean)

@JSExportTopLevel("HelloVuescale")
object HelloVuescale {

  implicit class RichEvent(val event: dom.Event) extends AnyVal {
    @inline final def inputTarget: dom.html.Input =
      event.target.asInstanceOf[dom.html.Input]
  }

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
    .renderTags { vm =>
      <.div(
        <.h3(^.className := "title", vm.title),
        <.p(
          ^.className := "control",
          <.input(
            ^.className := "input",
            ^.typeName := "text",
            ^.onKeyUp := { e: dom.KeyboardEvent =>
              if (e.keyCode == KeyCode.Enter) {
                vm.todos.push(Todo(
                  vm.todos.length,
                  vm.input,
                  false
                ))
                vm.input = ""
                e.inputTarget.value = ""
              } else {
                vm.input = e.inputTarget.value
              }
            },
          )
        ),
        <.ul(for (todo <- vm.todos) yield {
          <.li(
            ^.onDblClick := (() => todo.done = !todo.done),
            ^.classMap := ClassMap(
              "done" -> todo.done
            ),
            todo.name
          )
        })
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
  }
}
