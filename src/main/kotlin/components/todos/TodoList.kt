package components.todos

import entities.Todo
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.ul

external interface TodoListComponentProps : Props {
    var todos: Array<Todo>
    var selectColor: (Int, String) -> Unit
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
}

class TodoListComponent(props: TodoListComponentProps) : RComponent<TodoListComponentProps, State>(props) {
    override fun RBuilder.render() {
        ul(classes = "todo-list") {
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
}

