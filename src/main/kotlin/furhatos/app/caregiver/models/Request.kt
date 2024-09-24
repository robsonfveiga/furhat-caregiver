package furhatos.app.caregiver.models

import furhatos.app.caregiver.enums.DataType

data class Request(
    val data: Any,
    val type: DataType
)