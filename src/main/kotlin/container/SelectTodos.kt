package container

import State
import actions.todos.DeleteTodo
import actions.todos.SelectColor
import actions.todos.ToggleTodo
import components.todos.todoList
import entities.Todo
import react.ComponentClass
import react.Props
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

private external interface TodoListStateProps : Props {
    var todos: Array<Todo>
}

private external interface TodoListDispatchProps : Props {
    var selectColor: (Int, String) -> Unit
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
}

external interface TodoListComponentProps : Props {
    var todos: Array<Todo>
    var selectColor: (Int, String) -> Unit
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
}

// TodoListComponentでReduxを利用する
val selectTodos: ComponentClass<Props> =
    rConnect<State, RAction, WrapperAction, Props, TodoListStateProps, TodoListDispatchProps, TodoListComponentProps>(
        // ComponentにセットするデータをStore(Reducer.kt)のstateから読み込む
        { state, _ ->
            todos = state.todos
        },
        // ComponentからStoreにDispatchするActionsをセットする
        { dispatch, _ ->
            selectColor = { todoId, color -> dispatch(SelectColor(todoId, color)) }
            toggleTodo = { todoId -> dispatch(ToggleTodo(todoId)) }
            deleteTodo = { todoId -> dispatch(DeleteTodo(todoId)) }
        }
        // セットしたデータとActionをComponentPropsに渡してComponentを読みこむ
    )(todoList.unsafeCast<ComponentClass<TodoListComponentProps>>())
