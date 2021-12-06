package features.filters

import actions.StatusFilterChanged
import entities.VisibilityFilter
import enums.CompletedStatus
import redux.RAction

val initState = VisibilityFilter(CompletedStatus.ALL, mutableListOf())

fun filtersReducer(state: VisibilityFilter = initState, action: RAction): VisibilityFilter =
    when (action) {
        is StatusFilterChanged -> state.copy(status = action.completedStatus())
        else -> state
    }