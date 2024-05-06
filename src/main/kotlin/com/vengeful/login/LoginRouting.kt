package com.vengeful.login


import com.vengeful.cache.InMemoryCache
import com.vengeful.cache.TokenCache
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import java.util.UUID


fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            val first = InMemoryCache.userList.firstOrNull { it.login == receive.login }

            if (first == null) {
                call.respond(HttpStatusCode.BadRequest, "User not found")
            } else {
                if (first.password == receive.password) {
                    val token = UUID.randomUUID().toString()
                    InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
                    call.respond(LoginResponceRemote(token = token))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid password")
                }
            }
        }
    }
}