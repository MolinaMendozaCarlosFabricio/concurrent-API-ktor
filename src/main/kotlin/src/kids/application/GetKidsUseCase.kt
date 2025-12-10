package com.example.src.kids.application

import com.example.src.kids.domain.models.Kid
import com.example.src.kids.domain.repository.KidRepository
import kotlinx.coroutines.coroutineScope

class GetKidsUseCase (private val repository: KidRepository) {
    suspend operator fun invoke(): List<Kid> = coroutineScope { repository.getAll() }
}