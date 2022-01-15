package components.header

import actions.todos.AddTodo
import react.FC
import react.Props
import react.dom.html.ReactHTML.header
import react.dom.html.ReactHTML.input
import react.redux.useDispatch
import react.useState
import redux.RAction
import redux.WrapperAction

val header = FC<Props> {
    var text by useState("")
    val dispatch = useDispatch<RAction, WrapperAction>()

    header {
        className = "header"
        input {
            className = "new-todo"
            placeholder = "What needs to be done?"
            value = text
            onChange = { text = it.target.value }
            onKeyDown = {
                if (it.key == "Enter" && text.isNotEmpty()) {
                    dispatch(AddTodo(text))
                    text = ""
                }
            }
        }
    }
}
