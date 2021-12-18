package components.todos

import components.modules.timesSolid
import entities.Todo
import enums.Color
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.HTMLInputElement
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.input
import react.dom.li
import react.dom.option
import react.dom.select
import react.fc

external interface TodoListItemProps : Props {
    var todo: Todo
    var onColorChange: (String) -> Unit
    var onCompletedChange: () -> Unit
    var onDelete: () -> Unit
}

private val todoListItem = fc<TodoListItemProps> { props ->
    li {
        div(classes = "view") {
            div(classes = "segment label") {
                input(classes = "toggle") {
                    attrs {
                        type = InputType.checkBox
                        checked = props.todo.completed
                        onChangeFunction = { props.onCompletedChange() }
                    }
                }
                div(classes = "todo-text") {
                    +props.todo.text
                }
            }
            div(classes = "segment buttons") {
                select(classes = "colorPicker") {
                    attrs {
                        val color = props.todo.color?.name ?: "".lowercase()
                        value = color
                        style = color
                        onChangeFunction = { event ->
                            val target = event.target as HTMLInputElement
                            props.onColorChange(target.value)
                        }
                    }
                    option {
                        attrs {
                            value = ""
                        }
                    }
                    Color.values().map { color ->
                        option {
                            attrs {
                                key = color.name
                                value = color.name
                            }
                            +color.name.replaceFirstChar { it.uppercase() }
                        }
                    }
                }
                button(classes = "destroy") {
                    attrs {
                        onClickFunction = { props.onDelete() }
                    }
                    timesSolid()
                }
            }
        }
    }
}

fun RBuilder.todoListItem(handlers: TodoListItemProps.() -> Unit) = child(todoListItem) { this.attrs(handlers) }
