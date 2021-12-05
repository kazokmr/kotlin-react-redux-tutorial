package entities

import enums.Color
import enums.CompletedStatus

data class VisibilityFilter(val status: CompletedStatus = CompletedStatus.ALL, val colors: MutableList<Color>)
