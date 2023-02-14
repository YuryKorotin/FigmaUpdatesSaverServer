package sync_figma_to_gitlab_server

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json


class SyncServer {
    fun start () {
        embeddedServer(Netty, 8080,
            watchPaths = emptyList(),
            configure = {
                connectionGroupSize = 2
                workerGroupSize = 5
                callGroupSize = 10
            },
            module = Application::module
        ).start(wait = true)
    }
}

