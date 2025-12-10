package com.example.src.kids.infrastructure.adapters

import com.example.src.kids.domain.models.Kid
import com.example.src.kids.domain.repository.KidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import kotlinx.serialization.json.Json

class KidDatasetManager: KidRepository {
    private val file: File
    private val mutex: Mutex = Mutex()

    constructor(file: File) {
        this.file = file
    }

    override suspend fun save(kid: Kid) = withContext(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            mutex.withLock {
                val kids = if (file.exists())
                    Json.decodeFromString<List<Kid>>(file.readText())
                else
                    emptyList<Kid>()

                val updated = kids + kid
                file.writeText(Json.encodeToString<List<Kid>>(updated))
            }
        }
    }

    override suspend fun getAll(): List<Kid> = withContext(Dispatchers.IO) {
        mutex.withLock {
            if(!file.exists())
                return@withLock emptyList<Kid>()
            Json.decodeFromString<List<Kid>>(file.readText())
        }
    }
}