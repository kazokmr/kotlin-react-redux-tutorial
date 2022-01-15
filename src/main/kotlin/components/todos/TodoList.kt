package components.todos

import State
import react.FC
import react.Props
import react.dom.html.ReactHTML.ul
import react.key
import react.redux.useSelector

val todoList = FC<Props> {

    val todoIds = useSelector<State, Array<Int>> { state -> state.todos.map { it.id }.toTypedArray() }

    ul {
        className = "todo-list"
        todoIds.forEach { todoId ->
            todoListItem {
                key = todoId.toString()
                this.todoId = todoId
            }
        }
    }
}
