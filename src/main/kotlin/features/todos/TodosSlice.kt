package features.todos

import actions.AddTodo
import actions.ToggleTodo
import entities.Todo
import enums.Color
import redux.RAction

val initialState = listOf(
    Todo(0, "Learn React", true, null),
    Todo(1, "Learn Redux", false, Color.PURPLE),
    Todo(2, "Build something fun!", false, Color.BLUE)
)

fun todosReducer(state: List<Todo> = initialState, action: RAction): List<Todo> {
    return when (action) {
        is AddTodo -> state.toMutableList().apply {
            add(Todo(action.id(state), action.text, false, null))
        }.toList()
        is ToggleTodo -> state.apply {
            map { if (it.id == action.id) it.copy(completed = !it.completed) else it }
        }
        else -> state
    }
}