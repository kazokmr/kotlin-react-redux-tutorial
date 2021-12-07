package actions.todos

import enums.Color
import redux.RAction

class SelectColor(val id: Int, private val color: String) : RAction {
    val getColor: () -> Color = { Color.valueOf(color.uppercase()) }
}