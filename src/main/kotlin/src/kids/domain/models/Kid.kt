package com.example.src.kids.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Kid(
    var id: Int,
    var name: String,
    var lastname: String,
    var age: UInt,
    var country: String,
    var good_kid: Boolean
)
