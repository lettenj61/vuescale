package vuescale
package tags
package internal

import scala.language.dynamics
import scalatags.generic

/** Builtin Vue.js custom component.
  */
trait VueTags[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  import bundle.all._

  lazy val component = tag("component")
  // Avoid conflict with `transition` style
  lazy val transitionTag = tag("transition")
  lazy val transitionGroup = tag("transition-group")
  lazy val keepAlive = tag("keep-alive")
  lazy val slot = tag("slot")
}

/** Attributes for Vue.js html templates which are aware of
  * arguments and modifiers
  */
trait AttrStack[Builder, Output <: FragT, FragT] {

  /** Concrete type of this attribute
    */
  type Repr <: AttrStack[Builder, Output, FragT]
  type Extender = AttrExtender[Builder, Output, FragT]

  def name: String
  def arg: Option[String]
  def modifiers: Seq[String]

  /** Returns a new attribute with its argument replaced with given [[String]].
   */
  def apply(s: String): Repr = extend(Some(s), this.modifiers)
  def append(ss: String*): Repr =
    extend(this.arg, this.modifiers ++ ss)

  /** Dynamically modify its modifiers via [[AttrExtender]]s.
    */
  def modify(f: Extender => Extender): AttrStack[Builder, Output, FragT] =
    f(new AttrExtender(this)).!

  def !(ss: String*): Repr = append(ss: _*)

  def toAttr: generic.Attr

  /** Create new instance of this attribute
    * with preserving exact same type
    */
  def extend: (Option[String], Seq[String]) => Repr
}

class AttrExtender[Builder, Output <: FragT, FragT](
  val base: AttrStack[Builder, Output, FragT]
) extends scala.Dynamic {

  private[this] val buffer = base.modifiers.toBuffer

  def ! = base.extend(base.arg, buffer.toVector)

  def selectDynamic(x: String): this.type = {
    buffer += x
    this
  }
}

/** Vue.js attributes.
  */
trait VueAttrs[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  import bundle.all._

  lazy val ref = attr("ref")
  lazy val slotA = attr("slot")
  lazy val scope = attr("scope")
  lazy val mode = attr("mode")
  lazy val appear = attr("appear")

  /* Transition events. */
  lazy val enterClass = attr("enter-class")
  lazy val leaveClass = attr("leave-class")
  lazy val appearClass = attr("appear-class")
  lazy val enterToClass = attr("enter-to-class")
  lazy val leaveToClass = attr("leave-to-class")
  lazy val appearToClass = attr("apear-to-class")
  lazy val enterActiveClass = attr("enter-active-class")
  lazy val leaveActiveClass = attr("leave-active-class")
  lazy val appearActiveClass = attr("appear-active-class")
}

/** Vue.js directives.
  */
trait VueDirectives[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  import bundle.all._

  lazy val vBind = Binding()
  lazy val vOn = Listener()
  lazy val vText = attr("v-text")
  lazy val vHtml = attr("v-html")
  lazy val vShow = attr("v-show")
  lazy val vIf = attr("v-if")
  lazy val vElse = attr("v-else").empty
  lazy val vElseIf = attr("v-else-if")
  lazy val vFor = attr("v-for")
  lazy val vModel = attr("v-model")
  lazy val vPre = attr("v-pre").empty
  lazy val vCloak = attr("v-cloak").empty
  lazy val vOnce = attr("v-once").empty

  def directive(baseName: String) = Directive(baseName)

  abstract class AbstractDirective(val name: String)
      extends AttrStack[Builder, Output, FragT] {

    def toAttr: generic.Attr = {
      val argName = arg.map(x => ":" + x).getOrElse("")
      val dot = if (modifiers.isEmpty) "" else "."
      val adds = modifiers.mkString(".")
      attr(s"${name}${argName}${dot}${adds}")
    }
  }

  /** Attribute builder for custom directives.
    */
  case class Directive(
    baseName: String,
    arg: Option[String] = None,
    modifiers: Seq[String] = Nil
  ) extends AbstractDirective(s"v-${baseName}") {

    type Repr = Directive
    val extend = { (newArg: Option[String], newMod: Seq[String]) =>
      copy(baseName, newArg, newMod)
    }
  }

  /** Attribute builder for `v-bind`.
    */
  case class Binding(
    arg: Option[String] = None,
    modifiers: Seq[String] = Nil
  ) extends AbstractDirective("v-bind") {

    type Repr = Binding
    val extend = copy _

    /* Typical `v-bind` properties. */
    def cls = apply("class")
    def style = apply("style")
    def key = apply("key")
    def is = apply("is")

    /* `v-bind` modifiers. */
    def prop = append("prop")
    def camel = append("camel")
    def sync = append("sync")
  }

  /** Attribute builder for `v-on`.
    */
  case class Listener(
    arg: Option[String] = None,
    modifiers: Seq[String] = Nil
  ) extends AbstractDirective("v-on") {

    type Repr = Listener
    val extend = copy _

    /* Well-known `v-on` properties */
    def click = apply("click")
    def keyup = apply("keyup")
    def keydown = apply("keydown")
    def submit = apply("submit")

    /* For `transition` component. */
    def beforeEnter = apply("before-enter")
    def beforeLeave = apply("before-leave")
    def beforeAppear = apply("before-appear")
    def afterEnter = apply("after-enter")
    def afterLeave = apply("after-leave")
    def afterAppear = apply("after-appear")
    def enterCancelled = apply("enter-cancelled")
    def leaveCancelled = apply("leave-cancelled")
    def appearCancelled = apply("appear-cancelled")

    /* Supported `v-on` modifiers */
    def stop = append("stop")
    def prevent = append("prevent")
    def capture = append("capture")
    def self = append("self")
    def native = append("native")
    def once = append("once")
    def left = append("left")
    def right = append("right")
    def middle = append("middle")
    def passive = append("passive")
    def keyCode(code: Int) = append(code.toString)
    def keyAlias(alias: String) = append(alias)
  }
}

