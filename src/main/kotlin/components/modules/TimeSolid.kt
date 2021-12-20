package components.modules

import react.RBuilder
import react.dom.img

@JsModule("./times-solid.svg")
external val timesSolid: dynamic

fun RBuilder.timesSolid() {
    img(src = timesSolid) { }
}
