package entities

import enums.Color

data class Todo(val id: Int, val text: String, val completed: Boolean, val color: Color? = null)
