package components.footer

import State
import actions.filters.ChangeColorFilter
import actions.filters.ChangeStatusFilter
import actions.todos.ClearCompleted
import actions.todos.CompleteAll
import entities.VisibilityFilter
import enums.Color
import enums.CompletedStatus
import kotlinext.js.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.strong
import react.dom.html.ReactHTML.ul
import react.key
import react.redux.useDispatch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction

external interface RemainingTodosProps : Props {
    var count: Int
}

private val remainingTodos = FC<RemainingTodosProps> { props ->
    div {
        className = "todo-count"
        h5 {
            +"Remaining Todos"
        }
        strong {
            +"${props.count}"
        }
        +" item${if (props.count > 1) "s" else ""} left"
    }
}

external interface StatusFilterProps : Props {
    var status: CompletedStatus
    var onChange: (CompletedStatus) -> Unit
}

private val statusFilter = FC<StatusFilterProps> { props ->
    div {
        className = "filters statusFilters"
        h5 {
            +"Filter by Status"
        }
        ul {
            renderedFilters {
                status = props.status
                onChange = props.onChange
            }
        }
    }
}

private val renderedFilters = FC<StatusFilterProps> { props ->
    CompletedStatus.values().forEach { completedStatus ->
        li {
            key = completedStatus.name
            button {
                className = if (completedStatus == props.status) "selected" else ""
                onClick = { props.onChange(completedStatus) }
                +completedStatus.name.lowercase().replaceFirstChar { it.uppercase() }
            }
        }
    }
}

external interface ColorFiltersProps : Props {
    var colors: Array<Color>
    var onChange: (Color, String) -> Unit
}

private val colorFilters = FC<ColorFiltersProps> { props ->

    div {
        className = "filters colorFilters"
        h5 {
            +"Filter by Color"
        }
        form {
            className = "colorSelection"
            renderedColors {
                colors = props.colors
                onChange = props.onChange
            }
        }
    }
}

private val renderedColors = FC<ColorFiltersProps> { props ->
    Color.values().forEach { color ->
        val checkedColor = props.colors.contains(color)
        label {
            key = color.name.lowercase()
            input {
                type = react.dom.html.InputType.checkbox
                name = color.name.lowercase()
                checked = checkedColor
                onChange = {
                    val changeType = if (checkedColor) "removed" else "added"
                    props.onChange(color, changeType)
                }
            }
            span {
                className = "color-block"
                style = jso {
                    backgroundColor = csstype.Color(color.name)
                }
            }
            +color.name.lowercase().replaceFirstChar { it.uppercase() }
        }
    }
}

val footer = FC<Props> {

    val dispatch = useDispatch<RAction, WrapperAction>()

    val todosRemaining = useSelector<State, Int> { state -> state.todos.filter { todo -> !todo.completed }.size }
    val filter = useSelector<State, VisibilityFilter> { state -> state.visibilityFilter }
    val filterColors = filter.colors
    val filterStatus = filter.status

    val onColorChange: (Color, String) -> Unit = { color, changeType ->
        dispatch(ChangeColorFilter(color.name, changeType))
    }

    val onStatusChange: (CompletedStatus) -> Unit = { completedStatus ->
        dispatch(ChangeStatusFilter(completedStatus.name))
    }

    footer {
        className = "footer"
        div {
            className = "actions"
            h5 {
                +"Actions"
            }
            button {
                className = "button"
                onClick = { dispatch(CompleteAll()) }
                +"Mark All Completed"
            }
            button {
                className = "button"
                onClick = { dispatch(ClearCompleted()) }
                +"Clear Completed"
            }
        }
        remainingTodos {
            count = todosRemaining
        }
        statusFilter {
            status = filterStatus
            onChange = onStatusChange
        }
        colorFilters {
            colors = filterColors
            onChange = onColorChange
        }
    }
}
