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
<script type="text/javascript" src="../vuescale-example-fastopt.js"></script>
<div id="app">
    <h3>{{ morning.message }}, Vue.js!</h3>
    <ul>
        <li v-for="treasure in treasures">
            It costs {{treasure}} yen in JPY!
        </li>
    </ul>
</div>
<script>vuescale.example.Hello().main();</script>
```

```scala
package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.prelude._

@JSExportTopLevel("vuescale.example.Hello")
object Hello {

  @ScalaJSDefined
  class GreetingPage extends js.Object {
    val isChecked: Boolean = true
    val treasures: js.Array[Int] = js.Array(120, 460, 1080, 9600)
    val greets = js.Dictionary(
      "morning" -> "Ohayo", "afternoon" -> "Konnichiwa",
      "night" -> "Konbanwa", "sleep" -> "Oyasumi"
    )
  }

  @JSExport
  def main(): Unit = {

    val opts = new ComponentOptions[Vue, GreetingPage](
      el = "#app",
      initData = new GreetingPage
    )
    val vm: Vue = new Vue(opts.build())
  }
}
```

[sjs]:https://www.scala-js.org/
[vue]:https://vuejs.org/

[example]:https://github.com/lettenj61/vuescale/blob/master/example/src/main/scala/vuescale/example/Hello.scala
