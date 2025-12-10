package com.example

import com.example.src.kids.application.CreateKidUseCase
import com.example.src.kids.application.GetKidsUseCase
import com.example.src.kids.infrastructure.adapters.KidDatasetManager
import com.example.src.kids.infrastructure.controllers.KidController
import com.example.src.kids.infrastructure.kidRoutes
import com.example.src.wishlist.application.CreateWishlistUseCase
import com.example.src.wishlist.application.GetWishlistByKidUseCase
import com.example.src.wishlist.infrastructure.adapters.WishlistDatasetManager
import com.example.src.wishlist.infrastructure.controllers.WishlistController
import com.example.src.wishlist.infrastructure.wishlistRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.routing
import java.io.File
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) { json() }

    val kidRepo = KidDatasetManager(File("kids.json"))

    val getKids = GetKidsUseCase(kidRepo)
    val createKid = CreateKidUseCase(kidRepo)

    val kidController = KidController(getKids, createKid)

    val wishlistRepo = WishlistDatasetManager(File("wishlist.json"))

    val createWishlist = CreateWishlistUseCase(wishlistRepo)
    val getWishlistByKid = GetWishlistByKidUseCase(wishlistRepo)

    val wishlistController = WishlistController(
        createWishlist,
        getWishlistByKid
    )

    routing {
        kidRoutes(kidController)
        wishlistRoutes(wishlistController)
    }
}
