package actions

import entities.Todo
import redux.RAction

class AddTodo(val text: String) : RAction {
    val id: (MutableList<Todo>) -> Int = { todos -> todos.maxOfOrNull { it.id } ?: 0 }
}