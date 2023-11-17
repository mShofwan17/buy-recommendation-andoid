package me.skripsi.roomdb.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.skripsi.roomdb.BuyRecommendationDatabase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RoomDependencies {
    fun provideDatabase() : BuyRecommendationDatabase
}