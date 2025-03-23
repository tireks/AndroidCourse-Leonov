package com.tirexmurina.facevolume.shared.domain.usecase

import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import javax.inject.Inject

class GetRandomContactUseCase @Inject constructor(
    private val repository: ContactRepository
){
    suspend operator fun invoke() = repository.getRandomContact()
}