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


fun Application.getDbRouting() {
    routing {
        get("/getdb") {
            val db = getRoomDatabase(getDatabaseBuilder())
            val userDao = db.getDao()
            CoroutineScope(EmptyCoroutineContext).launch(Dispatchers.IO) {
                val users: List<User> = userDao.getAllItems()
                if (users.isNotEmpty()) {users.forEach { println("Login:${it.login}, Email:${it.email}, Password:${it.password}, Token:${it.token} ") }} else println("Sorry, empty DB!")
            }
        }
    }
}