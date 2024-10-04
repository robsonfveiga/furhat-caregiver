package furhatos.app.caregiver.flow

import SocketIOClientService
import furhatos.app.caregiver.enums.Action
import furhatos.app.caregiver.enums.DataType
import furhatos.app.caregiver.gestures.GestureUtils
import furhatos.app.caregiver.models.Request
import furhatos.app.caregiver.models.Response
import furhatos.event.Event
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.furhat.characters.Characters
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.voice.AzureVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.skills.Skill.getSkillProperties

class ServerResponseEvent(val response: Response): Event()

val Conversation = state {
    val socket = SocketIOClientService(getSkillProperties().getProperty("server"))
    socket.listen()
    onEntry {
        print(furhat.masks)
        socket.send("process", Request(Action.WELCOME, DataType.EVENT))
    }

    //Server Response
    onEvent<ServerResponseEvent> {
        it.response.printPretty()
        val gesture = GestureUtils.create(
            it.response.gesture.name,
            it.response.gesture.strength,
            it.response.gesture.duration
        )

        if (it.response.gesture.addAtBeginning) furhat.gesture(gesture, true)
        furhat.say(furhat.voice.style(it.response.speech, it.response.voice.style))
        if (!it.response.gesture.addAtBeginning) furhat.gesture(gesture, true)

        furhat.listen(1000000)
    }

    //User Response
    onResponse {
        socket.send("process", Request(it.text, DataType.SPEECH))
    }

    onNoResponse() {
        socket.send("process", Request(Action.NO_RESPONSE, DataType.EVENT))
    }
}