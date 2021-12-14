package components.footer

import enums.Color
import enums.CompletedStatus
import kotlinx.css.backgroundColor
import kotlinx.html.InputType
import kotlinx.html.classes
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.footer
import react.dom.form
import react.dom.h5
import react.dom.input
import react.dom.label
import react.dom.li
import react.dom.strong
import react.dom.ul
import react.fc
import styled.css
import styled.styledSpan

private fun RBuilder.remainingTodos(count: Int) {
    div(classes = "todo-count") {
        h5 {
            +"Remaining Todos"
        }
        strong {
            +"$count"
        }
        +" item${if (count > 1) "s" else ""} left"
    }
}

private fun RBuilder.renderedFilters(status: CompletedStatus, onChange: (CompletedStatus) -> Unit) =
    CompletedStatus.values().forEach {
        li {
            attrs {
                key = it.name
            }
            button(classes = if (it == status) "selected" else "") {
                val completedStatus = it
                attrs {
                    onClickFunction = { onChange(completedStatus) }
                }
                +it.name.lowercase().replaceFirstChar { it.uppercase() }
            }
        }
    }


private fun RBuilder.statusFilter(status: CompletedStatus, onChange: (CompletedStatus) -> Unit) {

    div(classes = "filters statusFilters") {
        h5 {
            +"Filter by Status"
        }
        ul {
            renderedFilters(status, onChange)
        }
    }
}

private fun RBuilder.renderedColors(colors: List<Color>, onChange: (Color, String) -> Unit) =
    Color.values().forEach {
        label {
            attrs {
                key = it.name.lowercase()
            }
            input {
                val color = it
                attrs {
                    type = InputType.checkBox
                    name = it.name.lowercase()
                    checked = colors.contains(it)
                    onChangeFunction = {
                        onChange(
                            color,
                            if (checked) "removed" else "added"
                        )
                    }
                }
            }
            styledSpan {
                attrs {
                    classes = setOf("color-block")
                }
                css {
                    backgroundColor = kotlinx.css.Color(it.name)
                }
            }
            +it.name.lowercase().replaceFirstChar { it.uppercase() }
        }
    }


private fun RBuilder.colorFilters(colors: List<Color>, onChange: (Color, String) -> Unit) {

    div(classes = "filters colorFilters") {
        h5 {
            +"Filter by Color"
        }
        form(classes = "colorSelection") {
            renderedColors(colors, onChange)
        }
    }
}

private val footer = fc<Props> {

    val colors = emptyList<Color>().toMutableList()
    val status = CompletedStatus.ALL
    val todosRemaining = 1

    val onStatusChange: (CompletedStatus) -> Unit = {
        console.log("Status change: ${it.name}")
    }
    val onColorChange: (Color, String) -> Unit = { color, changeType ->
        console.log("Color change: ${color.name} $changeType")
    }


    footer(classes = "footer") {
        div(classes = "actions") {
            h5 {
                +"Actions"
            }
            button(classes = "button") {
                +"Mark ALL Completed"
            }
            button(classes = "button") {
                +"Clear Completed"
            }
        }
        remainingTodos(todosRemaining)
        statusFilter(status, onStatusChange)
        colorFilters(colors, onColorChange)
    }
}

fun RBuilder.footer() = child(footer)