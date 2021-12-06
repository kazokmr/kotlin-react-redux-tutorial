import entities.Todo
import entities.VisibilityFilter
import features.filters.filtersReducer
import features.todos.todosReducer
import redux.Reducer
import redux.combineReducers
import kotlin.reflect.KProperty1

// Stateとして管理するオブジェクトをDataクラスで保持する
data class State(
    val todos: List<Todo>,
    val visibilityFilter: VisibilityFilter
)

// 分割しているReducerをマッピングする
fun combinedReducers() = combineReducers(
    mapOf(
        State::todos to ::todosReducer,
        State::visibilityFilter to ::filtersReducer
    )
)

/*
* ReducerのマッピングKeyを型で定義する
* 本来のcombineReducersのマッピングでは Keyは文字列("todos","visibilityFilter"など)で定義する。
* また、Dataクラスのプロパティを変えたら、Keyも変える必要がある。
* よってプロパティにKey名を追従させて変更時にコンパイルエラーが起こるように、Dataクラスのプロパティ名をKeyから参照するようにする
* */
fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> =
    combineReducers(reducers.mapKeys { it.key.name })
