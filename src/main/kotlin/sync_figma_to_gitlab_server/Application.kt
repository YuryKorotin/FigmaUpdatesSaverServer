package sync_figma_to_gitlab_server

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)
    }
    install(Compression) {
        gzip()
    }
    routing {
        get("/") {
            call.respondText(
                text = "Hello!! You are here in Figma sync server",
                contentType = ContentType.Text.Plain
            )
        }
        get("/figmaCallback") {
            call.respond(HttpStatusCode.OK, BaseResponse("FIGMA CALLBACK"))
        }
        get("/figmaCallback/{id}") {
            val id = call.parameters["id"]
            call.respond(HttpStatusCode.OK, BaseResponse("CALLBACK ${id}"))
        }
    }
}

data class BaseResponse<T>(val data: T? = null, val error: String? = null)