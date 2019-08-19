# Vuescale

Yet another Vue.js bindings for Scala.js

## The goal

Our objectives are:
- Provide effectively working set of [Vue.js][vue] API bindings for [Scala.js][scalajs]
- Provide Scala friendly tool to build client web applications with power of Vue.js

But currently most of the things are work in progress.

## Development

You need [sbt][] and **Scala.js plugin for sbt** to build the project. Follow Scala.js general build instruction [here][scalajs-build-instruction].

### Build tasks

#### To run all the tests

First, you need to run `yarn` or `npm install` to grab `jsdom`.

Then in sbt shell:

```sh
> core/test
```

#### To generate example application

In sbt shell:

```sh
> example/fullOptJS
```

Then open `./example/target/scala-2.xx/classes/index.html` with your favorite browser.


[sbt]:https://www.scala-sbt.org/
[scalajs]:https://www.scala-js.org/
[scalajs-build-instruction]:https://www.scala-js.org/doc/project/
[vue]:https://vuejs.org/
[example]:https://github.com/lettenj61/vuescale/blob/master/example/src/main/scala/vuescale/example/Hello.scala
