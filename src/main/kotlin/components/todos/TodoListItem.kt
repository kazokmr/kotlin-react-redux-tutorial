package components.todos

import components.modules.timesSolid
import entities.Todo
import enums.Color
import kotlinx.css.color
import kotlinx.html.InputType
import kotlinx.html.classes
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLSelectElement
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.input
import react.dom.li
import react.dom.option
import react.fc
import styled.css
import styled.styledSelect

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
                val todoColor = props.todo.color?.name ?: "".lowercase()
                styledSelect {
                    css {
                        color = kotlinx.css.Color(todoColor)
                    }
                    attrs {
                        classes = setOf("colorPicker")
                        value = todoColor.replaceFirstChar { it.uppercase() }
                        onChangeFunction = { event ->
                            val target = event.target as HTMLSelectElement
                            props.onColorChange(target.value)
                        }
                    }
                    option {
                        attrs {
                            value = ""
                        }
                    }
                    Color.values().forEach { color ->
                        option {
                            attrs {
                                key = color.name
                                value = color.name
                            }
                            +color.name.lowercase().replaceFirstChar { it.uppercase() }
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
