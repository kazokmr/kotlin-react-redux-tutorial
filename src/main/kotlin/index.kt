import components.app
import kotlinext.js.require
import kotlinx.browser.document
import react.dom.render

fun main() {
    require("./index.css")
    require("./primitiveui.css")
    document.getElementById("root")?.let { render(it) { child(app) } }
}
