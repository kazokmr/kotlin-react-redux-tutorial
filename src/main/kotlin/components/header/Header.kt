package components.header

import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.header
import react.dom.input
import react.fc
import react.useState

private val header = fc<Props> {
    var text by useState("")

    header(classes = "header") {
        input(classes = "new-todo") {
            attrs {
                placeholder = "What needs to be done?"
                value = text
                onChangeFunction = { event ->
                    val target = event.target as HTMLInputElement
                    text = target.value
                }
            }
        }
    }
}

fun RBuilder.header() = child(header)
