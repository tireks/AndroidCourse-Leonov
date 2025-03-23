package com.tirexmurina.facevolume.shared.data.remote

import com.tirexmurina.facevolume.shared.data.remote.model.RandomContactResponse
import retrofit2.http.GET

interface ContactService {

    @GET("api/")
    suspend fun getRandomUser() : RandomContactResponse

}