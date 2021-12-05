import actions.AddTodo
import actions.StatusFilterChanged
import actions.ToggleTodo
import entities.Todo
import entities.VisibilityFilter
import enums.Color
import enums.CompletedStatus
import redux.RAction

val initialState = State(
    mutableListOf(
        Todo(0, "Learn React", true, null),
        Todo(1, "Learn Redux", false, Color.PURPLE),
        Todo(2, "Build something fun!", false, Color.BLUE)
    ),
    VisibilityFilter(CompletedStatus.ALL, mutableListOf())
)

fun appReducer(state: State = initialState, action: RAction): State =
    when (action) {
        is AddTodo -> state.apply { todos.add(Todo(action.id(todos), action.text, false, null)) }
        is ToggleTodo -> state.apply {
            todos.map { if (it.id == action.id) it.copy(completed = !it.completed) else it }
        }
        is StatusFilterChanged -> state.copy(visibilityFilter = state.visibilityFilter.copy(status = action.completedStatus()))
        else -> state
    }