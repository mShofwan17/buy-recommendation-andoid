package me.skripsi.roomdb.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.skripsi.roomdb.BuyRecommendedDatabase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RoomDependencies {
    fun provideDatabase() : BuyRecommendedDatabase
}