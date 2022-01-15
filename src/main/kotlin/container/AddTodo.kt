package container

import actions.todos.AddTodo
import components.header.header
import react.ComponentClass
import react.Props
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

external interface TodoAddedProp : Props {
    var addTodo: (String) -> Unit
}

val todoAdded: ComponentClass<Props> =
    rConnect<RAction, WrapperAction, Props, TodoAddedProp>(
        { dispatch, _ ->
            addTodo = { text -> dispatch(AddTodo(text)) }
        }
    )(header.unsafeCast<ComponentClass<TodoAddedProp>>())
