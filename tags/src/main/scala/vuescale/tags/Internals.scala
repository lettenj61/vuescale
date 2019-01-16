package vuescale
package tags

import org.scalajs.dom
import vuescale.facade._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

object internal {

  /* Native attribute modifiers */

  trait AttributeKey[V] {
    def name: String
    def :=(value: V): VAttribute
  }

  sealed class NumberAttributeKey(val name: String) extends AttributeKey[Double] {
    def :=(value: Double): VAttribute = VAttribute(name, value)
    def :=(value: Int): VAttribute = VAttribute(name, value)
  }

  sealed class AttributeFromValue[V](val name: String)(implicit ev: V => js.Any)
    extends AttributeKey[V] {
    def :=(value: V): VAttribute = VAttribute(name, ev(value))
  }

  final class AsStringAttribute[V](val name: String) extends AttributeKey[V] {
    def :=(value: V): VAttribute = VAttribute(name, value.toString)
  }

  final class VoidAttribute(override val name: String) extends AttributeFromValue[Boolean](name)

  trait AttributeFactory {
    def attr[V](name: String)(implicit ev: V => js.Any): AttributeKey[V] =
      new AttributeFromValue[V](name)

    protected[this] def boolAttr(name: String): AttributeKey[Boolean] =
      new AttributeFromValue[Boolean](name)

    protected[this] def intAttr(name: String): AttributeKey[Int] =
      new AttributeFromValue[Int](name)

    protected[this] def doubleAttr(name: String): AttributeKey[Double] =
      new NumberAttributeKey(name)

    protected[this] def stringAttr(name: String): AttributeKey[String] =
      new AttributeFromValue[String](name)

    // TODO: Consider replacing it with simple `boolAttr`s
    protected[this] def voidAttr(name: String): VoidAttribute =
      new VoidAttribute(name)

    protected[this] def asStringAttr[V](name: String): AsStringAttribute[V] =
      new AsStringAttribute[V](name)
  }

  // TODO: I have no idea what key should live in Attributes or DomProps

  trait Attributes extends AttributeFactory {

    def attr(name: String): AttributeKey[js.Any] = new AttributeFromValue[js.Any](name)

    lazy val accept: AttributeKey[String] = stringAttr("accept")
    lazy val acceptCharset: AttributeKey[String] = stringAttr("accept-charset")
    lazy val action: AttributeKey[String] = stringAttr("action")
    lazy val accesskey: AttributeKey[String] = stringAttr("accesskey")
    lazy val alt: AttributeKey[String] = stringAttr("alt")
    lazy val autocapitalize: AttributeKey[String] = stringAttr("autocapitalize")
    lazy val autocomplete: AttributeKey[Boolean] = asStringAttr[Boolean]("autocomplete")

    lazy val contenteditable: AttributeKey[Boolean] = boolAttr("contenteditable")
    lazy val contextmenu: AttributeKey[String] = stringAttr("contextmenu")

    lazy val crossorigin: AttributeKey[String] = stringAttr("crossorigin")

    lazy val decoding: AttributeKey[String] = stringAttr("decoding")
    lazy val dir: AttributeKey[String] = stringAttr("dir")
    lazy val draggable: VoidAttribute = voidAttr("draggable")
    lazy val dropzone: AttributeKey[String] = stringAttr("dropzone")

    // TODO: more typo-safe API?
    lazy val enctype: AttributeKey[String] = stringAttr("enctype")

    lazy val height: AttributeKey[Int] = intAttr("height")
    lazy val hidden: VoidAttribute = voidAttr("hidden")
    lazy val id: AttributeKey[String] = stringAttr("id")
    // TODO: Is it enabled for <component :is="..."> template?
    lazy val is: AttributeKey[String] = stringAttr("is")
    lazy val lang: AttributeKey[String] = stringAttr("lang")
    lazy val maxlength: AttributeKey[Int] = intAttr("maxlength")
    lazy val minlength: AttributeKey[Int] = intAttr("minlength")
    lazy val media: AttributeKey[String] = stringAttr("media")
    lazy val method: AttributeKey[String] = stringAttr("method")
    lazy val name: AttributeKey[String] = stringAttr("name")
    lazy val novalidate: VoidAttribute = voidAttr("novalidate")
    // optimum
    // pattern
    lazy val placeholder: AttributeKey[String] = stringAttr("placeholder")
    lazy val readonly: VoidAttribute = voidAttr("readonly")
    // rel
    lazy val required: VoidAttribute = voidAttr("required")
    lazy val role: AttributeKey[String] = stringAttr("role")
    lazy val rows: AttributeKey[Int] = intAttr("rows")
    lazy val rowspan: AttributeKey[Int] = intAttr("rowspan")
    lazy val size: AttributeKey[Int] = intAttr("size")
    lazy val spellcheck: AttributeKey[Boolean] = boolAttr("spellcheck")
    // TODO: slot is now live in KeysAndBindings
    // lazy val slot: AttributeKey[String] = stringAttr("slot")
    lazy val src: AttributeKey[String] = stringAttr("src")
    // TODO: style is now live in KeysAndBindings
    // lazy val style: AttributeKey[String] = stringAttr("style")
    lazy val tabindex: AttributeKey[Int] = intAttr("tabindex")
    lazy val target: AttributeKey[String] = stringAttr("target")
    lazy val title: AttributeKey[String] = stringAttr("title")
    lazy val `type`: AttributeKey[String] = stringAttr("type")
    @inline final def tpe: AttributeKey[String] = `type`
    @inline final def typeName: AttributeKey[String] = `type`
    // translate
    // xmlns
  }

  /* Vue.js specific keys/bindings for VNode descriptors */

  sealed trait BindingKey[V] {
    def name: String
    def :=(value: V): VBinding
  }

  abstract class ClassBinding[V] extends BindingKey[V] {
    final val name = "class"
  }

  abstract class StyleBinding[V] extends BindingKey[V] {
    final val name = "style"
  }

  trait KeysAndBindings {

    protected[this] def bind[V](to: String)(implicit ev: V => js.Any): BindingKey[V] =
      new BindingKey[V] {
        val name = to
        def :=(value: V): VBinding = VBinding(name, value)
      }

    lazy val slot: BindingKey[String] = bind("slot")

    object classMap extends ClassBinding[Iterable[(String, Boolean)]] {
      def :=(iterable: Iterable[(String, Boolean)]): VBinding =
        VBinding(name, js.Dictionary(iterable.toSeq: _*))

      def :=(jsMap: ClassMap): VBinding = VBinding(name, jsMap)
    }

    object className extends ClassBinding[String] {
      def :=(names: String): VBinding =
        VBinding(
          name,
          js.Dictionary(names.split(" ").map((_, true)): _*)
        )
    }

    object styleMap extends StyleBinding[Iterable[(String, String)]] {
      def :=(value: Iterable[(String, String)]): VBinding =
        VBinding(name, js.Dictionary(value.toSeq: _*))

      def :=(jsMap: StyleMap): VBinding = VBinding(name, jsMap)
    }

    object props extends BindingKey[Iterable[(String, js.Any)]] {
      val name: String = "props"

      def :=(value: Iterable[(String, js.Any)]): VBinding =
        VBinding(name, js.Dictionary(value.toSeq: _*))

      def :=[R <: js.Object](value: R): VBinding =
        VBinding(name, value)
    }

    object text {
      def :=(value: String): VText = VText(value)
    }

  }

  sealed trait DomPropKey[V] {
    def name: String
    def :=(value: V): VDomProp
  }

  abstract class AbstractDomProp[V](val name: String)(implicit ev: V => js.Any) extends DomPropKey[V] {
    def :=(value: V): VDomProp = VDomProp(name, value)
  }

  trait DomProps {
    protected[this] def makeDomProp[V](name: String)(implicit ev: V => js.Any): DomPropKey[V] =
      new AbstractDomProp[V](name) {}

    lazy val innerHTML: DomPropKey[String] = makeDomProp[String]("innerHTML")
  }

  /* Vue.js event handler modifiers */

  sealed abstract class EventKey[E <: dom.Event](val name: String) {
    def :=(handler: E => _): VEventListener =
      VEventListener(name, handler)

    def :=(handler: () => _): VEventListener =
      VEventListener(name, (_: dom.Event) => handler())
  }

  trait Events {
    protected[this] def makeEvent[E <: dom.Event](eventName: String): EventKey[E] =
      new EventKey[E](eventName) {}

    protected[this] def mouseEvent(eventName: String): EventKey[dom.MouseEvent] =
      makeEvent[dom.MouseEvent](eventName)

    lazy val onClick: EventKey[dom.MouseEvent] = mouseEvent("click")
    lazy val onDblClick: EventKey[dom.MouseEvent] = mouseEvent("dblclick")
    lazy val onMouseDown: EventKey[dom.MouseEvent] = mouseEvent("mousedown")
    lazy val onMouseMove: EventKey[dom.MouseEvent] = mouseEvent("mousemove")
    lazy val onMouseOut: EventKey[dom.MouseEvent] = mouseEvent("mouseout")
    lazy val onMouseOver: EventKey[dom.MouseEvent] = mouseEvent("mouseover")
    lazy val onMouseLeave: EventKey[dom.MouseEvent] = mouseEvent("mouseleave")
    lazy val onMouseEnter: EventKey[dom.MouseEvent] = mouseEvent("mouseenter")
    lazy val onMouseUp: EventKey[dom.MouseEvent] = mouseEvent("mouseup")

    lazy val onKeyDown: EventKey[dom.KeyboardEvent] = makeEvent[dom.KeyboardEvent]("keydown")
    lazy val onKeyUp: EventKey[dom.KeyboardEvent] = makeEvent[dom.KeyboardEvent]("keyup")
    lazy val onKeyPress: EventKey[dom.KeyboardEvent] = makeEvent[dom.KeyboardEvent]("keypress")

    lazy val onInput: EventKey[dom.Event] = makeEvent[dom.Event]("input")
  }

  /* VNode tag modifiers */

  class TagName(final val name: String) {
    def apply(frags: VFragment*): VTag = {
      val children = js.Array[VTag]()
      val modifiers = js.Array[VModifier]()
      frags.foreach {
        case VTagSeq(tags) => children ++= tags
        case t: VTag => children += t
        case m: VModifier => modifiers += m
      }

      VTag(name, modifiers, children)
    }
  }

  trait TagFactory {
    def tag(tagName: String): TagName =
      new TagName(tagName)
  }

  trait Tags extends TagFactory {
    lazy val a: TagName = tag("a")
    lazy val area: TagName = tag("area")
    lazy val audio: TagName = tag("audio")
    lazy val b: TagName = tag("b")
    lazy val base: TagName = tag("base")
    lazy val blockquote: TagName = tag("blockquote")
    lazy val body: TagName = tag("body")
    lazy val br: TagName = tag("br")
    lazy val button: TagName = tag("button")
    lazy val caption: TagName = tag("caption")
    lazy val canvas: TagName = tag("canvas")
    lazy val cite: TagName = tag("cite")
    lazy val code: TagName = tag("code")
    lazy val col: TagName = tag("col")
    lazy val colgroup: TagName = tag("colgroup")
    lazy val datalist: TagName = tag("datalist")
    lazy val dd: TagName = tag("dd")
    lazy val del: TagName = tag("del")
    lazy val div: TagName = tag("div")
    lazy val dl: TagName = tag("dl")
    lazy val dt: TagName = tag("dt")
    lazy val em: TagName = tag("em")
    lazy val embed: TagName = tag("embed")
    lazy val fieldset: TagName = tag("fieldset")
    lazy val figure: TagName = tag("figure")
    lazy val figcaption: TagName = tag("figcaption")
    lazy val footer: TagName = tag("footer")
    lazy val form: TagName = tag("form")
    lazy val h1: TagName = tag("h1")
    lazy val h2: TagName = tag("h2")
    lazy val h3: TagName = tag("h3")
    lazy val h4: TagName = tag("h4")
    lazy val h5: TagName = tag("h5")
    lazy val h6: TagName = tag("h6")
    lazy val head: TagName = tag("head")
    lazy val header: TagName = tag("header")
    lazy val hr: TagName = tag("hr")
    lazy val iframe: TagName = tag("iframe")
    lazy val img: TagName = tag("img")
    lazy val input: TagName = tag("input")
    lazy val ins: TagName = tag("ins")
    lazy val label: TagName = tag("label")
    lazy val legend: TagName = tag("legend")
    lazy val li: TagName = tag("li")
    lazy val link: TagName = tag("link")
    lazy val map: TagName = tag("map")
    lazy val meta: TagName = tag("meta")
    lazy val `object`: TagName = tag("object")
    @inline final def objectTag: TagName = `object`
    lazy val ol: TagName = tag("ol")
    lazy val optgroup: TagName = tag("optgroup")
    lazy val option: TagName = tag("option")
    lazy val p: TagName = tag("p")
    lazy val param: TagName = tag("param")
    lazy val pre: TagName = tag("pre")
    lazy val s: TagName = tag("s")
    lazy val script: TagName = tag("script")
    lazy val select: TagName = tag("select")
    lazy val small: TagName = tag("small")
    lazy val source: TagName = tag("source")
    lazy val span: TagName = tag("span")
    lazy val strong: TagName = tag("strong")
    lazy val sub: TagName = tag("sub")
    lazy val sup: TagName = tag("sup")
    lazy val table: TagName = tag("table")
    lazy val tbody: TagName = tag("tbody")
    lazy val td: TagName = tag("td")
    lazy val textarea: TagName = tag("textarea")
    lazy val tfoot: TagName = tag("tfoot")
    lazy val th: TagName = tag("th")
    lazy val thead: TagName = tag("thead")
    lazy val tr: TagName = tag("tr")
    lazy val track: TagName = tag("track")
    lazy val u: TagName = tag("u")
    lazy val ul: TagName = tag("ul")
    lazy val video: TagName = tag("video")
    lazy val wbr: TagName = tag("wbr")
  }

  trait LowPriorityTags extends TagFactory {
    lazy val abbr: TagName = tag("abbr")
    lazy val article: TagName = tag("article")
    lazy val aside: TagName = tag("aside")
    lazy val bdi: TagName = tag("bdi")
    lazy val bdo: TagName = tag("bdo")
    lazy val command: TagName = tag("command")
    lazy val data: TagName = tag("data")
    lazy val details: TagName = tag("details")
    lazy val dfn: TagName = tag("dfn")
    lazy val kbd: TagName = tag("kbd")
    lazy val keygen: TagName = tag("keygen")
    lazy val main: TagName = tag("main")
    lazy val mark: TagName = tag("mark")
    lazy val math: TagName = tag("math")
    lazy val menu: TagName = tag("menu")
    lazy val meter: TagName = tag("meter")
    lazy val nav: TagName = tag("nav")
    lazy val noscript: TagName = tag("noscript")
    lazy val output: TagName = tag("output")
    lazy val progress: TagName = tag("progress")
    lazy val q: TagName = tag("q")
    lazy val ruby: TagName = tag("ruby")
    lazy val rp: TagName = tag("rp")
    lazy val rt: TagName = tag("rt")
    lazy val samp: TagName = tag("samp")
    lazy val section: TagName = tag("section")
    lazy val styleTag: TagName = tag("style")
    lazy val summary: TagName = tag("summary")
    lazy val time: TagName = tag("time")
    lazy val titleTag: TagName = tag("title")
    lazy val `var`: TagName = tag("var")
    @inline final def varTag: TagName = `var`
  }

  /* Fragments */

  sealed trait VFragment

  sealed trait VModifier extends VFragment {
    def name: String
    def value: js.Any
  }

  case class VAttribute(name: String, value: js.Any) extends VModifier

  case class VBinding(name: String, value: js.Any) extends VModifier

  case class VDomProp(name: String, value: js.Any) extends VModifier

  case class VEventListener(
    name: String,
    value: js.Function1[_, _],
    native: Boolean = false
  ) extends VModifier

  case class VText(value: js.Any) extends VModifier {
    final val name: String = "text"
  }

  case class VTagSeq(tags: Seq[VTag]) extends VFragment

  case class VTag(
    tag: String,
    modifiers: Seq[VModifier],
    childNodes: Seq[VTag] = Nil
  ) extends VFragment {
    def apply(nodes: VTag*): VTag = copy(childNodes = nodes.toSeq)

    def decodeModifiers: (Option[String], js.Dictionary[js.Any]) = {
      val options: js.Dictionary[js.Any] = js.Dictionary()
      val nativeAttrs: js.Dictionary[js.Any] = js.Dictionary()
      val domProps: js.Dictionary[js.Any] = js.Dictionary()
      val nativeHandlers: js.Dictionary[js.Function] = js.Dictionary()
      val vdomHandlers: js.Dictionary[js.Function] = js.Dictionary()
      val buffer = StringBuilder.newBuilder

      modifiers.foreach {
        case VAttribute(attr, v) =>
          nativeAttrs(attr) = v
        case VBinding(k, v) =>
          options(k) = v
        case VDomProp(k, v) =>
          domProps(k) = v
        case VEventListener(ev, fn, native) =>
          if (native) {
            nativeHandlers(ev) = fn
          } else {
            vdomHandlers(ev) = fn
          }
        case VText(text) => (text: Any) match {
          case s: String => buffer ++= s
          case _ => buffer ++= text.toString
        }
      }

      if (domProps.nonEmpty) {
        options("domProps") = domProps
      }
      if (nativeAttrs.nonEmpty) {
        options("attrs") = nativeAttrs
      }
      if (nativeHandlers.nonEmpty) {
        options("nativeOn") = nativeHandlers
      }
      if (vdomHandlers.nonEmpty) {
        options("on") = vdomHandlers
      }

      val textNode = if (buffer.nonEmpty) Some(buffer.result) else None
      (textNode, options)
    }

    lazy val renderFn: RenderFunction = {
      createElement: CreateElement => {
        val (textNode, options) = decodeModifiers
        val nodes: js.Array[VNode] = {
          val nodes0 = childNodes.map(_.renderFn(createElement)).toJSArray
          textNode.foreach(v => v.asInstanceOf[VNode] +=: nodes0)
          nodes0
        }
        createElement(tag, options, nodes)
      }
    }

    def render(implicit h: CreateElement): VNode =
      renderFn(h)
  }

  /* Utilities */

  type ClassMap = js.Dictionary[Boolean]
  type StyleMap = js.Dictionary[String]
}