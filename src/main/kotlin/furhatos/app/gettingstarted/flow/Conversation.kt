package furhatos.app.gettingstarted.flow

import SocketIOClientService
import furhatos.app.gettingstarted.enums.Action
import furhatos.app.gettingstarted.enums.DataType
import furhatos.app.gettingstarted.gestures.GestureUtils
import furhatos.app.gettingstarted.models.Request
import furhatos.app.gettingstarted.models.Response
import furhatos.event.Event
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.skills.Skill.getSkillProperties

class ServerResponseEvent(val response: Response): Event()

val Conversation = state {
    val socket = SocketIOClientService(getSkillProperties().getProperty("server"))
    socket.listen()

    onEntry {
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