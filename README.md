# Vuescale

Yet another Vue.js bindings for Scala.js

# The goal

Our objectives are:
- Provide effectively working set of [Vue.js][vue] API bindings for [Scala.js][scalajs]
- Provide Scala friendly tool to build client web applications with power of Vue.js

But currently most of the things are work in progress.

# Library at a glance

Currently, this snippet from [example sources][example] below is all I can show you.

```html
<div id="app">
  <h3>{{ title }}</h3>
  <input type="text" v-model="input" @keyup.enter="addTodo">
  <div v-for="todo in todos" :key="todo.id">
    <input type="checkbox" @click="checkTodo(todo)" :checked="todo.done">
    <span v-if="todo.done">
      <s>{{ todo.name }}</s>
    </span>
    <span v-else>{{ todo.name }}</span>
  </div>
  <todo-footer></todo-footer>
</div>
<script>
  vuescale.example.Hello.main();
</script>
```

```scala
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
    .result

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
```

[scalajs]:https://www.scala-js.org/
[vue]:https://vuejs.org/

[example]:https://github.com/lettenj61/vuescale/blob/master/example/src/main/scala/vuescale/example/Hello.scala
