package actions.todos

import entities.Todo
import redux.RAction

class AddTodo(val text: String) : RAction {
    val id: (Array<Todo>) -> Int = { todos -> (todos.maxOfOrNull { it.id }?.plus(1)) ?: 0 }
}