package components.todos

import entities.Todo
import react.Props
import react.RBuilder
import react.dom.ul
import react.fc

private val todoList = fc<Props> {
    val todos = emptyList<Todo>().toMutableList()

    ul(classes = "todo-list") {
        todos.forEach {
            todoListItem {
                todo = it
                onColorChange
                onCompletedChange
                onDelete
            }
        }
    }
}

fun RBuilder.todoList() = child(todoList)
