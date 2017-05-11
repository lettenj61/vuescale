package vuescale
package tags
package internal

import scalatags.generic

/** Builtin Vue.js custom component.
  */
trait VueTags[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  lazy val component = bundle.all.tag("component")
  // Avoid conflict with `transition` style
  lazy val transitionTag = bundle.all.tag("transition")
  lazy val transitionGroup = bundle.all.tag("transition-group")
  lazy val keepAlive = bundle.all.tag("keep-alive")
  lazy val slot = bundle.all.tag("slot")
}

/** Attributes for Vue.js html templates which are aware of
  * arguments and modifiers
  */
trait AttrStack[Builder, Output <: FragT, FragT] {

  /** Concrete type of this attribute
    */
  type Repr <: AttrStack[Builder, Output, FragT]

  def name: String
  def arg: Option[String]
  def modifiers: Seq[String]

  /** Returns a new attribute with its argument replaced with given [[String]].
   */
  def apply(s: String): Repr = extend(Some(s), this.modifiers)
  def append(ss: String*): Repr =
    extend(this.arg, this.modifiers ++ ss)

  def toAttr: generic.Attr
  def void(implicit ev: generic.AttrValue[Builder, String]): generic.AttrPair[Builder, _] = {
    generic.AttrPair(toAttr, "", ev)
  }

  /** Create new instance of this attribute
    * with preserving exact same type
    */
  def extend: (Option[String], Seq[String]) => Repr
}

trait VueAttrs[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  lazy val ref = bundle.all.attr("ref")
  lazy val slotA = bundle.all.attr("slot")
  lazy val scope = bundle.all.attr("scope")
  lazy val mode = bundle.all.attr("mode")
  lazy val appear = bundle.all.attr("appear")

  /* Transition events. */
  lazy val enterClass = bundle.all.attr("enter-class")
  lazy val leaveClass = bundle.all.attr("leave-class")
  lazy val appearClass = bundle.all.attr("appear-class")
  lazy val enterToClass = bundle.all.attr("enter-to-class")
  lazy val leaveToClass = bundle.all.attr("leave-to-class")
  lazy val appearToClass = bundle.all.attr("apear-to-class")
  lazy val enterActiveClass = bundle.all.attr("enter-active-class")
  lazy val leaveActiveClass = bundle.all.attr("leave-active-class")
  lazy val appearActiveClass = bundle.all.attr("appear-active-class")
}

/** Vue.js directives.
  */
trait VueDirectives[Builder, Output <: FragT, FragT] {
  self: VTagBundle[Builder, Output, FragT] =>

  lazy val vBind = Binding()
  lazy val vOn = Listener()
  lazy val vFor = bundle.all.attr("v-for")
  lazy val vIf = bundle.all.attr("v-if")
  lazy val vElse = bundle.all.attr("v-else")
  lazy val vElseIf = bundle.all.attr("v-else-if")
  lazy val vModel = bundle.all.attr("v-model")
  lazy val vText = bundle.all.attr("v-text")

  def directive(baseName: String) = Directive(baseName)

  abstract class AbstractDirective(val name: String)
      extends AttrStack[Builder, Output, FragT] {

    def toAttr: generic.Attr = {
      val argName = arg.map(x => ":" + x).getOrElse("")
      val dot = if (modifiers.isEmpty) "" else "."
      val adds = modifiers.mkString(".")
      bundle.all.attr(s"${name}${argName}${dot}${adds}")
    }
  }

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
