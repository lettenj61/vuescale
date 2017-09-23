package vuescale
package scaladsl

import scala.scalajs.js
import scala.scalajs.js.annotation._

import vuescale.facade.Vue

@ScalaJSDefined
trait DataOptions[V <: Vue] extends js.Object {
  def data: js.UndefOr[js.Function0[js.Object]] = js.undefined
  def props: js.UndefOr[js.Object] = js.undefined
  def propsData: js.UndefOr[js.Object] = js.undefined
  def computed: js.UndefOr[Handler[V]] = js.undefined
  def methods: js.UndefOr[Handler[V]] = js.undefined
  // TODO more stong type
  def watch: js.UndefOr[js.Dictionary[js.Any]] = js.undefined
}

@ScalaJSDefined
trait DomOptions[V <: Vue] extends js.Object {
  def el: js.UndefOr[String] = js.undefined
  def template: js.UndefOr[String] = js.undefined
  // TODO more strong type
  def render: js.UndefOr[js.Function] = js.undefined
  def renderError: js.UndefOr[js.Function] = js.undefined
}

@ScalaJSDefined
trait LifecycleOptions[V <: Vue] extends js.Object {
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

@ScalaJSDefined
trait AssetOptions[V <: Vue] extends js.Object {
  // TODO more clear type
  def directives: js.UndefOr[js.Object] = js.undefined
  def filters: js.UndefOr[js.Object] = js.undefined
  def components: js.UndefOr[js.Object] = js.undefined
}

@ScalaJSDefined
trait CompositionOptions[V <: Vue] extends js.Object {
  def parent: js.UndefOr[Vue] = js.undefined
  // TODO give clear type
  def mixins: js.UndefOr[js.Array[js.Object]] = js.undefined
  @JSName("extends")
  def `extends`: js.UndefOr[js.Object] = js.undefined
}

@ScalaJSDefined
trait Component[V <: Vue]
    extends DataOptions[V]
       with DomOptions[V]
       with LifecycleOptions[V]
       with AssetOptions[V]
       with CompositionOptions[V]
{
  def name: js.UndefOr[String] = js.undefined
  def functional: js.UndefOr[Boolean] = js.undefined
  // TODO define `ModelOptions`
  def model: js.UndefOr[js.Object] = js.undefined
}

object Component {

  def apply[V <: Vue](
    el: js.UndefOr[String] = js.undefined,
    data: js.UndefOr[js.Object] = js.undefined,
    props: js.UndefOr[js.Object] = js.undefined,
    computed: js.UndefOr[Handler[V]] = js.undefined,
    methods: js.UndefOr[Handler[V]] = js.undefined,
    template: js.UndefOr[String] = js.undefined
  ): Component[V] =
    applyInternal(el, data, props, computed, methods, template)

  // TODO support all options
  private def applyInternal[V <: Vue](
    _el: js.UndefOr[String],
    _data: js.UndefOr[js.Object],
    _props: js.UndefOr[js.Object],
    _computed: js.UndefOr[Handler[V]],
    _methods: js.UndefOr[Handler[V]],
    _template: js.UndefOr[String]
  ): Component[V] = new Component[V] {
    override val el = _el
    override val data = _data.map(x => { () => x })
    override val props = _props
    override val computed = _computed
    override val methods = _methods
    override val template = _template
  }
}