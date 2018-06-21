package vuescale
package scaladsl

import scala.language.implicitConversions

import scala.scalajs.js

import org.scalajs.dom

import vuescale.facade.CreateElement

/** Helper class to build components in type safe way.
 */
class Builder[V] private[scaladsl] (
  protected[this] val proxy: js.Dictionary[js.Any] = js.Dictionary()
) {

  private[scaladsl] def this(name: String) = this {
    js.Dictionary[js.Any]("name" -> name)
  }

  private[this] def update(key: String, value: js.Any): this.type = {
    proxy(key) = value
    this
  }

  def build: Component[V] = proxy.asInstanceOf[Component[V]]

  def el(selector: String): this.type =
    update("el", selector)

  def el(element: dom.Element): this.type =
    update("el", element)

  def template(string: String): this.type =
    update("template", string)

  def data(initData: js.Object): this.type =
    update("data", { () => initData })

  def data(fields: (String, Any)*): this.type =
    update("data", { () =>
      js.Dictionary(fields: _*)
    })

  def props(propNames: js.Array[String]): this.type =
    update("props", propNames)

  def propsData(data: js.Object): this.type =
    update("propsData", data)

  def computed(handler: Handler[_]): this.type =
    update("computed", handler)

  def methods(handler: Handler[_]): this.type =
    update("methods", handler)

  def components(children: (String, js.Any)*): this.type =
    update("components", js.Dictionary(children: _*))

  def name(componentName: String): this.type =
    update("name", componentName)

  def render(renderFn: js.Function1[CreateElement, js.Any]): this.type =
    update("render", renderFn)

  def render[V0](renderFn: js.ThisFunction1[V0, CreateElement, js.Any]): this.type =
    update("render", renderFn)
}

object Builder {
  implicit def dictionaryIsObject(dict: js.Dictionary[_]): js.Object =
    dict.asInstanceOf[js.Object]
}