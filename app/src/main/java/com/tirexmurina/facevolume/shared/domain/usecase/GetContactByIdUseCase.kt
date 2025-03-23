package com.tirexmurina.facevolume.shared.domain.usecase

import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(
    private val repository: ContactRepository
){
    suspend operator fun invoke(id : Long) = repository.getContactById(id)
}