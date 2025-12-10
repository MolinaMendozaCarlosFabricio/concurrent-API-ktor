package com.example.src.kids.domain.repository

import com.example.src.kids.domain.models.Kid

interface KidRepository {
    suspend fun save(kid: Kid)
    suspend fun getAll(): List<Kid>
}