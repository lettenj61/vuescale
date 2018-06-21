package vuescale
package scaladsl

import scala.scalajs.js

/**
  * Proxy is used to inject any properties of non-native (Scala.js defined)
  * JS classes into plain JS objects.
 */
abstract class Proxy[A] {
  protected[this] def proxy: js.Dictionary[js.Any]

  /**
    * Inject all function property defined in `template` into `proxy` object
    * this proxy points to.
   */
  protected def injectPrototypes[B <: js.Object](key: String, template: B): this.type = {
    val container: js.Dictionary[js.Any] = js.Dictionary()
    val rawObject = template.asInstanceOf[js.Dynamic]
    for {
      member <- js.Object.properties(template)
      if member != "constructor" // avoid inject `constructor` function
    } {
      val rawProp = rawObject.selectDynamic(member)
      js.typeOf(rawProp) match {
        case "function" =>
          container(member) = rawProp.asInstanceOf[js.Function].bind(container)
      }
    }
    proxy(key) = container
    this
  }
}