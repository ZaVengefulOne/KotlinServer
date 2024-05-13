package com.vengeful.data

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun Application.emptyDbRouting() {
    routing {
        post("/emptydb") {
            val db = getRoomDatabase(getDatabaseBuilder())
            val userDao = db.getDao()
            CoroutineScope(EmptyCoroutineContext).launch(Dispatchers.IO) {
                userDao.deleteAll()
            }
            call.respond(HttpStatusCode.Accepted, "Database emptied!")
        }
    }
}