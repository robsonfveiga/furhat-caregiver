import com.google.gson.Gson
import furhatos.app.gettingstarted.flow.ServerResponseEvent
import furhatos.app.gettingstarted.models.Request
import furhatos.app.gettingstarted.models.Response
import furhatos.event.EventSystem
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SocketIOClientService(server: String) {
    private val gson = Gson() // Initialize Gson

    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            gson()
        }
    }

    private val socket: Socket = IO.socket(server).apply { connect() }

    fun listen() {
        GlobalScope.launch {
            socket.on("response", Emitter.Listener { args ->
                if (args.isNotEmpty()) {
                    print(args)
                    val jsonResponse = args[0] as String
                    val responseBody: Response = gson.fromJson(jsonResponse, Response::class.java)
                    EventSystem.send(ServerResponseEvent(responseBody))
                }
            })
        }
    }

    fun send(event: String, body: Request) {
        GlobalScope.launch {
            socket.emit(event, gson.toJson(body))
        }
    }
}
