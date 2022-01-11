package components.header

import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onKeyDownFunction
import org.w3c.dom.HTMLInputElement
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.attrs
import react.dom.events.KeyboardEvent
import react.dom.header
import react.dom.input
import react.setState

external interface HeaderProps : Props {
    var addTodo: (String) -> Unit
}

external interface HeaderState : State {
    var text: String
}

@JsExport
class Header(props: HeaderProps) : RComponent<HeaderProps, HeaderState>(props) {

    override fun HeaderState.init(props: HeaderProps) {
        text = ""
    }

    override fun RBuilder.render() {
        header(classes = "header") {
            input(classes = "new-todo") {
                attrs {
                    placeholder = "What needs to be done?"
                    value = state.text
                    onChangeFunction = { event ->
                        val target = event.target as HTMLInputElement
                        setState { text = target.value }
                    }
                    onKeyDownFunction = { event ->
                        val target = event.target as HTMLInputElement
                        val trimmedText = target.value.trim()
                        val keyEvent = event as KeyboardEvent<*>
                        if (keyEvent.key == "Enter" && trimmedText.isNotEmpty()) {
                            props.addTodo(trimmedText)
                            setState { text = "" }
                        }
                    }
                }
            }
        }
    }
}
