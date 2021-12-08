package reducers.todos

import actions.todos.AddTodo
import actions.todos.ClearCompleted
import actions.todos.CompleteAll
import actions.todos.DeleteTodo
import actions.todos.SelectColor
import actions.todos.ToggleTodo
import entities.Todo
import enums.Color
import redux.RAction

val initialState = listOf(
    Todo(0, "Learn React", true),
    Todo(1, "Learn Redux", false, Color.PURPLE),
    Todo(2, "Build something fun!", false, Color.BLUE)
)

fun todosReducer(state: List<Todo> = initialState, action: RAction): List<Todo> {
    return when (action) {
        is AddTodo -> state.toMutableList().apply {
            add(Todo(action.id(state), action.text, false))
        }.toList()
        is ToggleTodo -> state.map { if (it.id == action.id) it.copy(completed = !it.completed) else it }
        is SelectColor -> state.map { if (it.id == action.id) it.copy(color = action.getColor()) else it }
        is DeleteTodo -> state.filter { it.id != action.id }
        is CompleteAll -> state.map { it.copy(completed = true) }
        is ClearCompleted -> state.filter { !it.completed }
        else -> state
    }
}