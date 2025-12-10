package com.example.src.kids.infrastructure.controllers

import com.example.src.kids.application.CreateKidUseCase
import com.example.src.kids.application.GetKidsUseCase
import com.example.src.kids.domain.models.Kid
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.request.receive


class KidController(
    private val getKids: GetKidsUseCase,
    private val createKid: CreateKidUseCase
) {

    suspend fun getAll(call: ApplicationCall) {
        val kids = getKids()
        call.respond(kids)
    }

    suspend fun create(call: ApplicationCall) {
        val kid = call.receive<Kid>()
        createKid(kid)

        call.respond(
            message = kid,
            status = HttpStatusCode.Created
        )
    }
}