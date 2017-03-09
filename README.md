# Vuescale

Yet another Vue.js bindings for Scala.js

# The goal

Our objectives are:
- Provide effectively working set of [Vue.js][vue] API bindings for [Scala.js][sjs]
- Provide Scala friendly tool to build client web applications with power of Vue.js

But currently most of the things are work in progress.

# Library at a glance

Currently, this snippet from [example sources][example] below is all I can show you.

```scala
package vuescale.example

import scala.scalajs.js
import js.annotation._

import vuescale.core.facades._

@JSExport
object Hello {

  @JSExport("Greeting") @JSExportAll
  case class Greeting(message: String)

  @JSExport
  def main(): Unit = {

    val model = js.Dictionary(
      "morning" -> Greeting("Ohayo"),
      "afternoon" -> Greeting("Konnichiwa"),
      "sleep" -> Greeting("Oyasumi")
    )
    val opts = new ComponentOptions[Vue] {
      override val el = js.defined("#app")
      override val data = { () => model }
    }
    val vm: Vue = new Vue(opts)
  }
}
```

[sjs]:https://www.scala-js.org/
[vue]:https://vuejs.org/

[example]:https://github.com/lettenj61/vuescale/blob/master/example/src/main/scala/vuescale/example/Hello.scala