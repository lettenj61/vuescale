package vuescale
package scaladsl

import scala.scalajs.js
import scala.scalajs.js.annotation._

import vuescale.facade.CreateElement
import vuescale.facade.Vue

trait DataOptions[V] extends js.Object {
  def data: js.UndefOr[js.Function0[js.Object]] = js.undefined
  def props: js.UndefOr[js.Object] = js.undefined
  def propsData: js.UndefOr[js.Object] = js.undefined
  def computed: js.UndefOr[Handler[_]] = js.undefined
  def methods: js.UndefOr[Handler[_]] = js.undefined
  // TODO more stong type
  def watch: js.UndefOr[js.Dictionary[js.Any]] = js.undefined
}

trait DomOptions extends js.Object {
  def el: js.UndefOr[String] = js.undefined
  def template: js.UndefOr[String] = js.undefined
  // TODO more strong type
  def render: js.UndefOr[CreateElement] = js.undefined
  def renderError: js.UndefOr[js.Function] = js.undefined
}

trait LifecycleOptions[V] extends js.Object {
  type LifecycleHook = js.ThisFunction0[V, Unit]

  def beforeCreate: js.UndefOr[LifecycleHook] = js.undefined
  def created: js.UndefOr[LifecycleHook] = js.undefined
  def beforeMount: js.UndefOr[LifecycleHook] = js.undefined
  def mounted: js.UndefOr[LifecycleHook] = js.undefined
  def beforeUpdate: js.UndefOr[LifecycleHook] = js.undefined
  def updated: js.UndefOr[LifecycleHook] = js.undefined
  def activated: js.UndefOr[LifecycleHook] = js.undefined
  def deactivated: js.UndefOr[LifecycleHook] = js.undefined
  def beforeDestroy: js.UndefOr[LifecycleHook] = js.undefined
  def destroyed: js.UndefOr[LifecycleHook] = js.undefined
}

trait AssetOptions extends js.Object {
  // TODO more clear type
  def directives: js.UndefOr[js.Object] = js.undefined
  def filters: js.UndefOr[js.Object] = js.undefined
  def components: js.UndefOr[js.Dictionary[js.Any]] = js.undefined
}

trait CompositionOptions extends js.Object {
  def parent: js.UndefOr[Vue] = js.undefined
  // TODO give clear type
  def mixins: js.UndefOr[js.Array[js.Object]] = js.undefined
  @JSName("extends")
  def `extends`: js.UndefOr[js.Object] = js.undefined
}

trait Component[V]
    extends DataOptions[V]
       with DomOptions
       with LifecycleOptions[V]
       with AssetOptions
       with CompositionOptions
{
  def name: js.UndefOr[String] = js.undefined
  def functional: js.UndefOr[Boolean] = js.undefined
  // TODO define `ModelOptions`
  def model: js.UndefOr[js.Object] = js.undefined
}

object Component {

  def apply(
    el: js.UndefOr[String] = js.undefined,
    data: js.UndefOr[js.Object] = js.undefined,
    props: js.UndefOr[js.Object] = js.undefined,
    computed: js.UndefOr[Handler[_]] = js.undefined,
    methods: js.UndefOr[Handler[_]] = js.undefined,
    template: js.UndefOr[String] = js.undefined,
    components: js.UndefOr[js.Dictionary[js.Any]] = js.undefined,
    render: js.UndefOr[js.Function] = js.undefined
  ): Component[Vue] =
    applyWithRawValues[Vue](data)(
      "el" -> el, "props" -> props, "computed" -> computed, "methods" -> methods,
      "template" -> template, "components" -> components, "render" -> render
    )

  private def applyWithRawValues[V <: Vue](
    data: js.UndefOr[js.Object])(rest: (String, js.Any)*): Component[V] = {

    val wrappedDataFn: js.UndefOr[js.Function0[js.Object]] =
      data.map(x => { () => x })

    val opts: js.Dictionary[js.Any] = js.Dictionary()

    if (!js.isUndefined(wrappedDataFn)) {
      opts("data") = wrappedDataFn
    }

    rest.filterNot(js.isUndefined).foreach { case (key, opt) => 
      opts(key) = opt
    }

    opts.asInstanceOf[Component[V]]
  }

  def builder[V <: Vue](name: String): Builder[V] = new Builder[V](name)

  def builder[V <: Vue]: Builder[V] = new Builder[V]()
}