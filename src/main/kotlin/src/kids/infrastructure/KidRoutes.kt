package com.example.src.kids.infrastructure

import com.example.src.kids.infrastructure.controllers.KidController
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.kidRoutes (controller: KidController) {
    route("/kids") {
        get {
            controller.getAll(call)
        }
        post {
            controller.create(call)
        }
    }
}