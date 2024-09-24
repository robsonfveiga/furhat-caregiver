package furhatos.app.gettingstarted.gestures

import furhatos.gestures.Gestures
import furhatos.gestures.Gesture
import kotlin.reflect.full.declaredFunctions

class GestureUtils {
    companion object {
        fun create(name: String, strength: Double = 1.0, duration: Double = 1.0): Gesture {
            val gesturesClass = Gestures::class
            val function = gesturesClass.declaredFunctions.find { it.name == name }

            //ToDo make the model generate the iterations.
            if (name.lowercase() in listOf("shake", "roll", "nod"))
                return function?.call(Gestures, strength, duration, 1) as Gesture

            return function?.call(Gestures, strength, duration) as Gesture
        }
    }
}