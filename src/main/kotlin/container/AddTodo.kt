package container

import actions.todos.AddTodo
import components.header.Header
import components.header.HeaderProps
import react.ComponentClass
import react.Props
import react.RBuilder
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

private val todoAdded: ComponentClass<Props> =
    rConnect<RAction, WrapperAction, Props, HeaderProps>(
        { dispatch, _ ->
            addTodo = { text -> dispatch(AddTodo(text)) }
        }
    )(Header::class.js.unsafeCast<ComponentClass<HeaderProps>>())

fun RBuilder.header() = child(todoAdded)