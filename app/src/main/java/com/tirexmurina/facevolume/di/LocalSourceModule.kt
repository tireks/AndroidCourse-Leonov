package com.tirexmurina.facevolume.di

import android.content.Context
import com.tirexmurina.facevolume.shared.data.local.ContactDao
import com.tirexmurina.facevolume.shared.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext app: Context): AppDatabase {
        return AppDatabase.getDatabase(app)
    }

    @Provides
    @Singleton
    fun provideTileDao(database: AppDatabase) : ContactDao = database.contactDao()
}