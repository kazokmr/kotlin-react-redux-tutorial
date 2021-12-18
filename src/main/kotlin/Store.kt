import entities.Todo
import entities.VisibilityFilter
import enums.Color
import enums.CompletedStatus
import redux.RAction
import redux.createStore
import redux.rEnhancer

// StateをDataクラスで保持する
data class State(
    val todos: Array<Todo> = emptyArray(),
    val visibilityFilter: VisibilityFilter = VisibilityFilter(CompletedStatus.ALL)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as State

        if (!todos.contentEquals(other.todos)) return false
        if (visibilityFilter != other.visibilityFilter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = todos.contentHashCode()
        result = 31 * result + visibilityFilter.hashCode()
        return result
    }
}

private val initialStateTodo = arrayOf(
    Todo(0, "Learn React", true),
    Todo(1, "Learn Redux", false, Color.PURPLE),
    Todo(2, "Build something fun!", false, Color.BLUE)
)

val store = createStore<State, RAction, dynamic>(::rootReducer, State(), rEnhancer())
