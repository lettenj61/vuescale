# Vuescale

Yet another Vue.js bindings for Scala.js

# The goal

Our objectives are:
- Provide effectively working set of [Vue.js][vue] API bindings for [Scala.js][sjs]
- Provide Scala friendly tool to build client web applications with power of Vue.js

But currently most of the things are work in progress.

# Library at a glance

Currently, this snippet from [example sources][example] below is all I can show you.

```html
<script src="https://unpkg.com/vue"></script>
<script src="../vuescale-example-fastopt.js"></script>
<div id="app">
    <h3>{{ greet }}</h3>
    <template v-if="treasures.length > 3">
      <ul>
          <li v-for="treasure in treasures">
              {{ treasure.name }} costs {{ treasure.price }} yen in JPY!
          </li>
      </ul>
      <p>Total cost: {{ sumOfTreasures }}</p>
    </template>
</div>
<script>
    vuescale.example.Hello.main();
</script>
```

```scala
package vuescale.example

import scala.scalajs.js
import scala.scalajs.js.annotation._

import vuescale.prelude._

@JSExportAll
case class Treasure(name: String, price: Int)

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @ScalaJSDefined
  class Example extends js.Object {
    val greet = "Hello, Vue.js"
    val treasures: js.Array[Treasure] = js.Array(
      Treasure("Gold piece", 799),
      Treasure("Silver ring", 1499),
      Treasure("Book of useful techniques", 6499),
      Treasure("Old game console", 24999)
    )
  }

  type ExampleView = Vue with Example 

  @JSExport
  def main(): Unit = {
    val vm: Vue = new Vue(Component[ExampleView](
      el = "#app",
      data = new Example,
      computed = new Handler[ExampleView] {
        val sumOfTreasures: Callback0[Int] = { (vm: ExampleView) =>
          vm.treasures.map(_.price).sum
        }
      }
    ))
  }
}
```

[sjs]:https://www.scala-js.org/
[vue]:https://vuejs.org/

[example]:https://github.com/lettenj61/vuescale/blob/redesign/example/src/main/scala/vuescale/example/Hello.scala
