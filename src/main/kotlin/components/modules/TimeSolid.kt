package components.modules

import react.RBuilder
import react.dom.div
import react.dom.img

@JsModule("./times-solid.svg")
external val timesSolid: dynamic

fun RBuilder.timesSolid() {
    div(classes = "logo") {
        img("delete", src = timesSolid as? String) { }
    }
}
