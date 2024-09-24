package furhatos.app.gettingstarted.models.properties

data class Gesture(
    val name: String,
    val addAtBeginning: Boolean,
    val strength: Double,
    val duration: Double,
    val reason: String,
)