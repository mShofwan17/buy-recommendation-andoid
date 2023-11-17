package me.skripsi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.skripsi.data.data_source.BerandaDataSource
import me.skripsi.data.data_source.ListDataUjiDataSource
import me.skripsi.data.repository.beranda.BerandaRepository
import me.skripsi.data.repository.beranda.BerandaRepositoryImpl
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.data.repository.list_data.ListDataUjiRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideListDataRepository(
       dataSource: ListDataUjiDataSource
    ) : ListDataUjiRepository {
        return ListDataUjiRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideBerandaRepository(
        dataSource: BerandaDataSource
    ): BerandaRepository = BerandaRepositoryImpl(dataSource)
}