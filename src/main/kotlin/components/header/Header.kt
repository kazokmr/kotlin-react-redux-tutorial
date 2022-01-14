package components.header

import react.FC
import react.Props
import react.dom.html.ReactHTML.header
import react.dom.html.ReactHTML.input
import react.useState

external interface HeaderProps : Props {
    var addTodo: (String) -> Unit
}

val header = FC<HeaderProps> { props ->
    var text by useState("")

    header {
        className = "header"
        input {
            className = "new-todo"
            placeholder = "What needs to be done?"
            value = text
            onChange = { text = it.target.value }
            onKeyDown = {
                if (it.key == "Enter" && text.isNotEmpty()) {
                    props.addTodo(text)
                    text = ""
                }
            }
        }
    }
}
