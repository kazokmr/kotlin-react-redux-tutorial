import entities.Todo
import entities.VisibilityFilter

data class State(val todos: MutableList<Todo>, val visibilityFilter: VisibilityFilter)

