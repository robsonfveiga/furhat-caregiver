package furhatos.app.gettingstarted.models

import furhatos.app.gettingstarted.enums.DataType

data class Request(
    val data: Any,
    val type: DataType
)