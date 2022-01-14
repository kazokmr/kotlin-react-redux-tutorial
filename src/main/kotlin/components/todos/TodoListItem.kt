package components.todos

import components.modules.timesSolid
import csstype.Color
import entities.Todo
import kotlinext.js.jso
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.key

external interface TodoListItemProps : Props {
    var todo: Todo
    var onColorChange: (String) -> Unit
    var onCompletedChange: () -> Unit
    var onDelete: () -> Unit
}

val todoListItem = FC<TodoListItemProps> { props ->
    li {
        div {
            className = "view"
            div {
                className = "segment label"
                input {
                    className = "toggle"
                    type = InputType.checkbox
                    checked = props.todo.completed
                    onChange = { props.onCompletedChange() }
                }
                div {
                    className = "todo-text"
                    +props.todo.text
                }
            }
            div {
                className = "segment buttons"
                select {
                    val todoColor = props.todo.color?.name ?: "".lowercase()
                    className = "colorPicker"
                    style = jso {
                        color = Color(todoColor)
                    }
                    value = todoColor.replaceFirstChar { it.uppercase() }
                    onChange = { props.onColorChange(it.target.value) }
                    option {
                        key = ""
                        value = ""
                    }
                    enums.Color.values().forEach { color ->
                        option {
                            key = color.name
                            value = color.name
                            +color.name.lowercase().replaceFirstChar { it.uppercase() }
                        }
                    }
                }
                button {
                    className = "destroy"
                    onClick = { props.onDelete() }
                    timesSolid()
                }
            }
        }
    }
}
