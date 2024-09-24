package furhatos.app.gettingstarted.models

import furhatos.app.gettingstarted.models.properties.Gesture
import furhatos.app.gettingstarted.models.properties.Voice

data class Response(
    val speech: String,
    val voice: Voice,
    val gesture: Gesture
){
    fun printPretty() {
        // ANSI escape codes for colors
        val RESET = "\u001B[0m"
        val GREEN = "\u001B[32m"
        val YELLOW = "\u001B[33m"
        val BLUE = "\u001B[34m"
        val CYAN = "\u001B[36m"

        // Add frame to the output
        println("\n========================================")
        println("${CYAN}Server Response:${RESET}")
        println("========================================")

        println("${GREEN}Speech:${RESET} $speech")
        println("${YELLOW}Voice:${RESET} ${voice.style}")
        println("${YELLOW}Reason:${RESET} ${voice.reason}")

        println("${BLUE}Gesture:${RESET} ${gesture.name}")
        println("${BLUE}Strength:${RESET} ${gesture.strength}")
        println("${BLUE}Duration:${RESET} ${gesture.duration}")
        println("${BLUE}Reason:${RESET} ${gesture.reason}")
        println("${BLUE}Position (At Beginning):${RESET} ${gesture.addAtBeginning}")

        // Add frame to the output
        println("================================================")
    }
}
