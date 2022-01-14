package container

import actions.todos.AddTodo
import components.header.HeaderProps
import components.header.header
import react.ComponentClass
import react.Props
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

val todoAdded: ComponentClass<Props> =
    rConnect<RAction, WrapperAction, Props, HeaderProps>(
        { dispatch, _ ->
            addTodo = { text -> dispatch(AddTodo(text)) }
        }
    )(header.unsafeCast<ComponentClass<HeaderProps>>())
