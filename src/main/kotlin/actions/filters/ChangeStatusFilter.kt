package actions.filters

import enums.CompletedStatus
import redux.RAction

class ChangeStatusFilter(private val status: String) : RAction {
    val completedStatus: () -> CompletedStatus = { CompletedStatus.valueOf(status.uppercase()) }
}