package com.tirexmurina.facevolume.di

import com.tirexmurina.facevolume.shared.data.ContactRepositoryImpl
import com.tirexmurina.facevolume.shared.data.local.FakeContacts
import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactModule {

    @Provides
    @Singleton
    fun provideFakeContacts() : FakeContacts = FakeContacts

    @Module
    @InstallIn(SingletonComponent::class)
    interface ContactModuleInt {
        @Binds
        @Singleton
        fun provideContactRepository(repository: ContactRepositoryImpl) : ContactRepository
    }

}