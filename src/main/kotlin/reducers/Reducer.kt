package reducers

import entities.Todo
import entities.VisibilityFilter
import reducers.filters.filtersReducer
import reducers.todos.todosReducer
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
* よってプロパティにKey名を追従させて変更時にコンパイルエラーが起こるようにする。
* 具体的にはKProperty1でState型とプロパティを受けることで、KProperty1.name で変換して、"プロパティ名 to Reducer" に変換している
* */
fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> =
    combineReducers(reducers.mapKeys { it.key.name })
