package vuescale
package scaladsl

import scala.scalajs.js

/** Base trait provides type alias for `js.ThisFunction` types.
 *
 *  Currently it supports functions with up to 7 arguments.
 */
trait BoundFunctionTemplate[This] extends js.Object {
  type Callback0[R] = js.ThisFunction0[This, R]
  type Callback1[A, R] = js.ThisFunction1[This, A, R]
  type Callback2[A, B, R] = js.ThisFunction2[This, A, B, R]
  type Callback3[A, B, C, R] = js.ThisFunction3[This, A, B, C, R]
  type Callback4[A, B, C, D, R] = js.ThisFunction4[This, A, B, C, D, R]
  type Callback5[A, B, C, D, E, R] = js.ThisFunction5[This, A, B, C, D, E, R]
  type Callback6[A, B, C, D, E, F, R] = js.ThisFunction6[This, A, B, C, D, E, F, R]
  type Callback7[A, B, C, D, E, F, G, R] = js.ThisFunction7[This, A, B, C, D, E, F, G, R]

  type Callback = Callback0[js.Any]
}
