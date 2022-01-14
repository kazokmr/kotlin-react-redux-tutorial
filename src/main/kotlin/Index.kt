import components.app
import kotlinext.js.require
import kotlinx.browser.document
import react.dom.render
import react.redux.provider

fun main() {
    require("./index.css")
    render(document.getElementById("root")!!) {
        provider(store) {
            app()
        }
    }
}
