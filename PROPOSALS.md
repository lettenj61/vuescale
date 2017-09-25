# Vuescale proposals

## Vue instance

Creating A `Vue` instance is pretty straightforward.

With JavaScript, you'll do:

```javascript
var vm = new Vue({
  el: '#app',
  data: {
    message: 'Hi!'
  }
});
```

And Vuescale counterpart:

```scala
import scala.scalajs.js
import vuescale.prelude._

val vm: Vue = new Vue(Component(
  el = "#app",
  data = js.Dictionary(
    "message" -> "Hi!"
  )
))
```

But most of the cases you'll want typed data:

```scala
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined // for Scala.js 0.6.x

import vuescale.prelude._

@ScalaJSDSefined
class Hero(val name: String, val power: Int) extends js.Object

val vm = new Vue(Component(
  // ...
  data = new Hero("Thunderbolt", 120)
))
```

Note that you can't use Scala types directly as `root` data object for Vue instance as it conflicts with the way Vue uses to merge its data option into itself, even the Scala class annotated with `JSExport`.

So the code below will fall to runtime error:

```scala
@JSExportAll
case class Hero(name: String, power: Int)

val vm = new Vue(Component(
  data = Hero("Firespark", 90)
  // => The properties of `Hero` object would not be found
))
```

To avoid this, you'll need to wrap Scala types in some object extends `js.Object`.

```scala
@ScalaJSDefined
class PageData(val title: String, val hero: Hero) extends js.Object

val vm = new Vue(Component(
  data = new PageData("Hero's Profiles", Hero("Firespark", 90))
))
```

The essence of Vuescale is it help you defining types for options of Vue component, which has very wide variant. Vuescale enables this with `Component` trait, as it appears in those code samples above.

Here is simplified definition of `Component`:

```scala
// TODO
@ScalaJSDefined
trait Component[V <: Vue] extends js.Object {

  def el: js.UndefOr[String] = js.undefined
  def data: js.UndefOr[js.Object] = js.undefined
  def computed: js.UndefOr[Handler[V]] = js.undefined
  def methods: js.UndefOr[Handler[V]] = js.undefined
  // more options to come ...
}
```


## Defining a component with Vuescale

First, give your component a type. There will be several ways to doing this.

Say we have following data structure:

```scala
@ScalaJSDefined
class Todo(
  val id: Int,
  val title: String,
  var doing: Boolean
) extends js.Object
```

When you don't have to access Vue instance methods, use standalone JS type.

```scala
@ScalaJSDefined
trait TodoList extends js.Object {
  var input: String
  var todos: js.Array[Todo]
}

// Inject it into Vue and use it
type TodoListView = Vue with TodoList 
new Vue(Component[TodoListView](
  data = new TodoList {
    var input = ""
    var todos = js.Array[Todo]()
  },
  methods = new Handler[TodoListView] {
    val addTodo: Callback = { (vm: TodoListView) =>
      vm.todos += new Todo(
        vm.todos.length,
        vm.input,
        false
      )
      vm.todo = ""
    }
  }
))
```

Here type `Callback` in `methods` object is merely type alias for `js.ThisFunction0[TodoListView, Unit]`. It is required because we have to tell Scala.js compiler to bind `this` context of our component option correctly. `Callback` is internal abstract type member of `Handler[V]`.

If you need access to Vue instance, declare the **ideal** type for your Component by extending `Vue`.

```scala
@js.native
trait TodoListView extends Vue with TodoList {
  def addTodo(): Unit
  def removeTodo(): Unit
}

// the handler
val todoMethods = new Handler[TodoListView] {
  val addTodo: Callback = { vm: TodoListView => 
    // We can use Vue instance here
    vm.$emit("newTodo")
  }
}
```

**Can we?**

```scala
@ScalaJSDefined
class TodoListHandler extends Handler[TodoList] {
  def addTodo: Callback = { vm =>
    // ...
  }
}
```


## Scalatags integration

```scala
import vuescale.scaladsl.tags.template.prefix._
import vuescale.scaladsl.tags.template.helper._

@JSExportAll
case class Hero(
  name: String,
  power: Int,
  bio: String
)

@ScalaJSDefined
class HeroBrowser extends js.Object {
  val heroes = js.Array[Hero]()
}

type HeroBrowserView = Vue with HeroBrowser

val HeroBrowserView = ScalatagsComponent.builder[HeroBrowserView]
  .name("hero-browser")
  .template(
    <.div(
      ^.vFor := "hero in heroes",
      ^.vBind.style := "heroStyle",
      <.p(m"hero.name"),
      <.p(
        <.span(vBind.style := "gradient(hero)"),
        <.span(<.a(
          ^.href := "toDetailUrl(hero.name)",
          m"hero.bio"
        ))
      )
    )
  )
  .methods(new Handler[HeroBrowser] {
    def toDetailUrl(name: String): String = // ...
    def gradient(hero: Hero): StyleMap = StyleMap(
      // ...
    )
  })

val vm = new Vue(Component(
  render = (h: CreateElement) => h(HeroBrowserView)
))
```