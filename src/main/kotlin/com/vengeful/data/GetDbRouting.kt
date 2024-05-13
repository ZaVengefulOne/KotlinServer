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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


fun Application.getDbRouting() {
    routing {
        get("/getdb") {
            val result = async { fetchdata() }.await()
            if (result.isNotEmpty()){
                call.respond(result)
            } else call.respond("Sorry empty db!")
        }
    }
}

suspend fun fetchdata(): String {
    val db = getRoomDatabase(getDatabaseBuilder())
    val userDao = db.getDao()
    var userString = ""
        val users: List<User> = userDao.getAllItems()
        if (users.isNotEmpty()) {
            users.forEach {
                userString += "Login:${it.login}, Email:${it.email}, Password:${it.password}, Token:${it.token} \n"
            }
        } else println("Sorry, empty DB!")
    return if (userString.isNotEmpty()) userString else ""
}