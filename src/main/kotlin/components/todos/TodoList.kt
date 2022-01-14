package components.todos

import entities.Todo
import react.FC
import react.Props
import react.dom.html.ReactHTML.ul
import react.key

external interface TodoListComponentProps : Props {
    var todos: Array<Todo>
    var selectColor: (Int, String) -> Unit
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
}

val todoList = FC<TodoListComponentProps> { props ->
    ul {
        className = "todo-list"
        props.todos.forEach {
            todoListItem {
                key = it.id.toString()
                todo = it
                onColorChange = { color -> props.selectColor(it.id, color) }
                onCompletedChange = { props.toggleTodo(it.id) }
                onDelete = { props.deleteTodo(it.id) }
            }
        }
    }
}
