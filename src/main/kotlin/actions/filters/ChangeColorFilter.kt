package actions.filters

import enums.Color
import redux.RAction

class ChangeColorFilter(private val color: String, val changeType: String) : RAction {
    val getColor: () -> Color = { Color.valueOf(color.uppercase()) }
}