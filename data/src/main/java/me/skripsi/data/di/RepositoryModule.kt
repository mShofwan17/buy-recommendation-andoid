package me.skripsi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.data.repository.list_data.ListDataUjiRepositoryImpl
import me.skripsi.roomdb.BuyRecommendedDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideListDataRepository(
        dbSource: BuyRecommendedDatabase
    ) : ListDataUjiRepository {
        return ListDataUjiRepositoryImpl(dbSource)
    }
}