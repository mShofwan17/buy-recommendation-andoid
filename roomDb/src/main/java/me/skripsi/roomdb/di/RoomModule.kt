package me.skripsi.roomdb.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.skripsi.roomdb.BuyRecommendedDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDb(
        @ApplicationContext context: Context
    ): BuyRecommendedDatabase {
        return Room.databaseBuilder(
            context = context,
            BuyRecommendedDatabase::class.java,
            name = "db_buy_recommended"
        ).build()
    }
}