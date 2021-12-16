package reducers.filters

import actions.filters.ChangeColorFilter
import actions.filters.ChangeStatusFilter
import entities.VisibilityFilter
import enums.CompletedStatus
import redux.RAction

val initState = VisibilityFilter(CompletedStatus.ALL)

fun filtersReducer(state: VisibilityFilter = initState, action: RAction): VisibilityFilter =
    when (action) {
        is ChangeStatusFilter -> state.copy(status = action.completedStatus())
        is ChangeColorFilter -> when (action.changeType) {
            "added" -> state.copy(colors = state.colors + action.getColor())
            "removed" -> state.copy(colors = state.colors.filter { color -> color != action.getColor() }.toTypedArray())
            else -> state
        }
        else -> state
    }