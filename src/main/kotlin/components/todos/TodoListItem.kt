package components.todos

import components.modules.timesSolid
import entities.Todo
import enums.Color
import kotlinx.css.color
import kotlinx.html.classes
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLSelectElement
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.html.InputType
import react.dom.html.ReactHTML
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
            // Checkboxのcheckedがuncontrollableと認識されてしまうので、この部分だけReactHTMLでdomを構築する
            ReactHTML.div {
                attrs.className = "segment label"
                ReactHTML.input {
                    attrs.className = "toggle"
                    attrs.type = InputType.checkbox
                    attrs.checked = props.todo.completed
                    attrs.onChange = { props.onCompletedChange() }
                }
                ReactHTML.div {
                    attrs.className = "todo-text"
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
