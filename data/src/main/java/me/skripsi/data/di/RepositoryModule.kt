package me.skripsi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.skripsi.data.data_source.BerandaDataSource
import me.skripsi.data.data_source.FormUjiDataSource
import me.skripsi.data.data_source.ListDataDataSource
import me.skripsi.data.data_source.ResultNaiveBayesDataSource
import me.skripsi.data.repository.beranda.BerandaRepository
import me.skripsi.data.repository.beranda.BerandaRepositoryImpl
import me.skripsi.data.repository.form_uji.FormUjiRepository
import me.skripsi.data.repository.form_uji.FormUjiRepositoryImpl
import me.skripsi.data.repository.hasil_uji.HasilUjiRepository
import me.skripsi.data.repository.hasil_uji.HasilUjiRepositoryImpl
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.data.repository.list_data.ListDataUjiRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideListDataRepository(
       dataSource: ListDataDataSource
    ) : ListDataUjiRepository {
        return ListDataUjiRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideBerandaRepository(
        dataSource: BerandaDataSource
    ): BerandaRepository = BerandaRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideFormUjiRepository(
        dataSource: FormUjiDataSource
    ): FormUjiRepository = FormUjiRepositoryImpl(dataSource)


    @Provides
    @Singleton
    fun provideHasilUjiRepository(
        dataSource: ResultNaiveBayesDataSource
    ): HasilUjiRepository = HasilUjiRepositoryImpl(dataSource)
}