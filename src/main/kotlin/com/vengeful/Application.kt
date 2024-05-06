package com.vengeful

import com.vengeful.data.User
import com.vengeful.data.getDatabaseBuilder
import com.vengeful.data.getDbRouting
import com.vengeful.data.getRoomDatabase
import com.vengeful.login.configureLoginRouting
import com.vengeful.plugins.*
import com.vengeful.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    getDbRouting()
}
