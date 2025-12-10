package com.example.src.kids.application

import com.example.src.kids.domain.models.Kid
import com.example.src.kids.domain.repository.KidRepository
import kotlinx.coroutines.supervisorScope

class CreateKidUseCase (private val repository: KidRepository) {
    suspend operator fun invoke(kid: Kid) = supervisorScope { repository.save(kid) }
}