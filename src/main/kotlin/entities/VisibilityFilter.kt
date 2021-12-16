package entities

import enums.Color
import enums.CompletedStatus

data class VisibilityFilter(
    val status: CompletedStatus = CompletedStatus.ALL,
    val colors: Array<Color> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as VisibilityFilter

        if (status != other.status) return false
        if (!colors.contentEquals(other.colors)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + colors.contentHashCode()
        return result
    }
}
