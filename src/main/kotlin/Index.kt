import components.app
import kotlinext.js.require
import kotlinx.browser.document
import react.dom.render
import react.redux.provider

fun main() {
    require("./index.css")
    require("./primitiveui.css")
    document.getElementById("root")?.let {
        render(it) {
            provider(store) {
                child(app)
            }
        }
    }
}
