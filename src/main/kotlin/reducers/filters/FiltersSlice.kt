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
            "added" -> state.let {
                it.copy(colors = it.colors.toMutableSet().apply { add(action.getColor()) }.apply { toMutableSet() })
            }
            "removed" -> state.apply { colors.filter { color -> color != action.getColor() } }
            else -> state
        }
        else -> state
    }