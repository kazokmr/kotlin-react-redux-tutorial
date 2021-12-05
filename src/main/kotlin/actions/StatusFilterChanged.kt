package actions

import enums.CompletedStatus
import redux.RAction

class StatusFilterChanged(val status: String) : RAction {
    val completedStatus: () -> CompletedStatus = { CompletedStatus.valueOf(status.uppercase()) }
}