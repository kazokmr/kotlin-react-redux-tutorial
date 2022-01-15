package components.todos

import State
import actions.todos.DeleteTodo
import actions.todos.SelectColor
import actions.todos.ToggleTodo
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
import react.redux.useDispatch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction

external interface TodoListItemProps : Props {
    var todoId: Int
}

val todoListItem = FC<TodoListItemProps> { props ->

    val todo = useSelector<State, Todo> { state -> state.todos.first { it.id == props.todoId } }
    val dispatch = useDispatch<RAction, WrapperAction>()

    li {
        div {
            className = "view"
            div {
                className = "segment label"
                input {
                    className = "toggle"
                    type = InputType.checkbox
                    checked = todo.completed
                    onChange = { dispatch(ToggleTodo(todo.id)) }
                }
                div {
                    className = "todo-text"
                    +todo.text
                }
            }
            div {
                className = "segment buttons"
                select {
                    val todoColor = todo.color?.name ?: "".lowercase()
                    className = "colorPicker"
                    style = jso {
                        color = Color(todoColor)
                    }
                    value = todoColor.replaceFirstChar { it.uppercase() }
                    onChange = { dispatch(SelectColor(todo.id, it.target.value)) }
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
                    onClick = { dispatch(DeleteTodo(todo.id)) }
                    timesSolid()
                }
            }
        }
    }
}
