package furhatos.app.caregiver.models.properties

data class Gesture(
    val name: String,
    val addAtBeginning: Boolean,
    val strength: Double,
    val duration: Double,
    val reason: String,
)