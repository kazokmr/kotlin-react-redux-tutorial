package reducers.todos

import actions.todos.AddTodo
import actions.todos.ClearCompleted
import actions.todos.CompleteAll
import actions.todos.DeleteTodo
import actions.todos.SelectColor
import actions.todos.ToggleTodo
import entities.Todo
import redux.RAction

fun todosReducer(state: Array<Todo> = emptyArray(), action: RAction): Array<Todo> = when (action) {
    is AddTodo -> state + Todo(action.id(state), action.text, false)
    is ToggleTodo -> state.map {
        if (it.id == action.id) it.copy(completed = !it.completed) else it
    }.toTypedArray()
    is SelectColor -> state.map {
        if (it.id == action.id) it.copy(color = action.getColor()) else it
    }.toTypedArray()
    is DeleteTodo -> state.filter { it.id != action.id }.toTypedArray()
    is CompleteAll -> state.map { it.copy(completed = true) }.toTypedArray()
    is ClearCompleted -> state.filter { !it.completed }.toTypedArray()
    else -> state
}
