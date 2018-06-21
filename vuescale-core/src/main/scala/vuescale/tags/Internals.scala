package vuescale
package tags

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

import org.scalajs.dom

import vuescale.facade.CreateElement
import vuescale.facade.RenderFunction
import vuescale.facade.VNode

object internal {

  /* Native attribute modifiers */

  trait AttributeKey[V] {
    def name: String
    def := (value: V): VAttribute
  }

  sealed abstract class AttributeTemplate[V](val name: String)(encode: V => js.Any)
      extends AttributeKey[V]
  {
    def := (value: V): VAttribute = VAttribute(name, encode(value))
  }

  sealed class NumberAttributeKey(val name: String) extends AttributeKey[Double] {
    def := (value: Double): VAttribute = VAttribute(name, value)
    def := (value: Int): VAttribute = VAttribute(name, value)
  }

  sealed class AttributeFromValue[V <% js.Any](val name: String)
      extends AttributeKey[V]
  {
    def := (value: V): VAttribute = VAttribute(name, value)
  }

  final class AsStringAttribute[V](val name: String) extends AttributeKey[V] {
    def := (value: V): VAttribute = VAttribute(name, value.toString)
  }
  final class VoidAttribute(override val name: String) extends AttributeFromValue[Boolean](name)

  trait AttributeFactory {
    protected[this] def booleanAttribute(name: String): AttributeKey[Boolean] =
      new AttributeFromValue[Boolean](name)

    protected[this] def intAttribute(name: String): AttributeKey[Int] =
      new AttributeFromValue[Int](name)

    protected[this] def doubleAttribute(name: String): AttributeKey[Double] =
      new NumberAttributeKey(name)

    protected[this] def stringAttribute(name: String): AttributeKey[String] =
      new AttributeFromValue[String](name)

    protected[this] def voidAttribute(name: String): VoidAttribute =
      new VoidAttribute(name)
  }

  trait Attributes extends AttributeFactory {

    lazy val accept: AttributeKey[String] = stringAttribute("accept")
    lazy val action: AttributeKey[String] = stringAttribute("action")
    lazy val accesskey: AttributeKey[String] = stringAttribute("accesskey")
    lazy val alt: AttributeKey[String] = stringAttribute("alt")
    // autocapitalize
    lazy val autocomplete: AttributeKey[Boolean] =
      new AsStringAttribute[Boolean]("autocomplete")

    lazy val hidden: VoidAttribute = voidAttribute("hidden")
    lazy val id: AttributeKey[String] = stringAttribute("id")
    lazy val lang: AttributeKey[String] = stringAttribute("lang")
    lazy val maxlength: AttributeKey[Int] = intAttribute("maxlength")
    lazy val minlength: AttributeKey[Int] = intAttribute("minlength")
    lazy val media: AttributeKey[String] = stringAttribute("media")
    lazy val method: AttributeKey[String] = stringAttribute("method")
    lazy val name: AttributeKey[String] = stringAttribute("name")
    // novalidate
    // optimum
    // pattern
    lazy val placeholder: AttributeKey[String] = stringAttribute("placeholder")
    lazy val readonly: VoidAttribute = voidAttribute("readonly")
    // rel
    lazy val required: VoidAttribute = voidAttribute("required")
    lazy val role: AttributeKey[String] = stringAttribute("role")
    lazy val rows: AttributeKey[Int] = intAttribute("rows")
    lazy val rowspan: AttributeKey[Int] = intAttribute("rowspan")
    lazy val size: AttributeKey[Int] = intAttribute("size")
    // spellcheck
    lazy val src: AttributeKey[String] = stringAttribute("src")
    lazy val tabindex: AttributeKey[Int] = intAttribute("tabindex")
    lazy val target: AttributeKey[String] = stringAttribute("target")
    lazy val title: AttributeKey[String] = stringAttribute("title")
    // translate
    // xmlns
  }

  /* Vue.js specific keys/bindings for VNode descriptors */

  sealed trait BindingKey[V] {
    def name: String
    def := (value: V): VBinding
  }

  abstract class ClassNameBinding(final val name: String)
      extends BindingKey[Iterable[(String, Boolean)]]
  {
    def := (iterable: Iterable[(String, Boolean)]): VBinding =
      VBinding(name, js.Dictionary(iterable.toSeq: _*))
    def := (jsMap: ClassMap): VBinding = VBinding(name, jsMap)
  }

  trait KeysAndBindings {
    object classMap extends ClassNameBinding("class")
    object props extends BindingKey[Map[String, js.Any]] {
      val name: String = "props"
      def := (value: Map[String, js.Any]): VBinding =
        VBinding(name, js.Dictionary(value.toSeq: _*))
    }
    object text {
      def := (value: String): VText = VText(value)
    }
  }

  sealed trait DomPropKey[V] {
    def name: String
    def := (value: V): VDomProp
  }

  abstract class AbstractDomProp[V <% js.Any](val name: String) extends DomPropKey[V] {
    def := (value: V): VDomProp = VDomProp(name, value)
  }

  trait DomProps {
    protected[this] def mkDomProp[V <% js.Any](name: String): DomPropKey[V] =
      new AbstractDomProp[V](name) {}

    lazy val innerHTML: DomPropKey[String] = mkDomProp[String]("innerHTML")
  }

  /* Vue.js event handler modifiers */

  sealed abstract class EventKey[E <: dom.Event, R](val name: String) {
    def := (handler: E => R): VEventListener[E, R] =
      VEventListener(name, handler)
  }

  trait Events {
    protected[this] def mkEvent[E <: dom.Event, R](eventName: String): EventKey[E, R] =
      new EventKey[E, R](eventName) {}

    object onClick extends EventKey[dom.MouseEvent, js.Any]("click")
  }

  /* VNode tag modifiers */

  trait TagName {
    def name: String
    def apply(attrs: VModifier*): VTag
  }

  abstract class AbstractTag(final val name: String) extends TagName {
    def apply(attrs: VModifier*): VTag =
      VTag(name, attrs.toSeq)
  }

  trait Tags {
    protected[this] def mkTag(tagName: String): TagName =
      new AbstractTag(tagName) {}

    lazy val a: TagName = mkTag("a")
    lazy val article: TagName = mkTag("article")
    lazy val aside: TagName = mkTag("aside")
    lazy val button: TagName = mkTag("button")
    lazy val div: TagName = mkTag("div")
    lazy val form: TagName = mkTag("form")
    lazy val li: TagName = mkTag("li")
    lazy val p: TagName = mkTag("p")
    lazy val section: TagName = mkTag("section")
    lazy val span: TagName = mkTag("span")
  }

  /* Fragments */

  sealed trait VModifier {
    def name: String
    def value: js.Any
  }
  case class VAttribute(name: String, value: js.Any) extends VModifier
  case class VBinding(name: String, value: js.Any) extends VModifier
  case class VDomProp(name: String, value: js.Any) extends VModifier
  case class VEventListener[E <: dom.Event, R](
    name: String,
    value: js.Function1[E, R],
    native: Boolean = false
  ) extends VModifier
  case class VText(value: js.Any) extends VModifier {
    final val name: String = "text"
  }

  case class VTag(
    tag: String,
    modifiers: Seq[VModifier],
    childNodes: Seq[VTag] = Nil
  ) {
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
          case s: String  => buffer ++= s
          case _          => buffer ++= (text.toString)
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

    def toRenderer: RenderFunction = {
      (createElement: CreateElement) => {
        val (textNode, options) = decodeModifiers
        val nodes = {
          val nodes0 = childNodes.map(_.toRenderer(createElement)).toJSArray
          textNode.foreach(v => v.asInstanceOf[VNode] +=: nodes0)
          nodes0
        }
        createElement(tag, options, nodes)
      }
    }
  }

  /* Utilities */

  type ClassMap = js.Dictionary[Boolean]
  object ClassMap {
    def apply(classDefs: (String, Boolean)*): js.Dictionary[Boolean] =
      js.Dictionary(classDefs: _*)
  }
}