package com.rounds.myapplication.di

import android.content.Context
import androidx.room.Room
import com.rounds.myapplication.data.room.AppDatabase
import com.rounds.myapplication.util.SQLCipherUtils
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {

        val userPassphrase = "There is no spoon".toCharArray()
        val passphrase = SQLiteDatabase.getBytes(userPassphrase)
        val state = SQLCipherUtils.getDatabaseState(context, AppDatabase.DB_NAME)

        if (state == SQLCipherUtils.State.UNENCRYPTED) {
            SQLCipherUtils.encrypt(
                context,
                AppDatabase.DB_NAME,
                passphrase
            )
        }
        val factory = SupportFactory(passphrase)
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .openHelperFactory(factory) // Add this line to add encryption to the database
            .build()

        return db
    }

    @Provides
    fun provideMoshi(): Moshi {
        val moshi = Moshi
            .Builder()
            .build()

        return moshi
    }
}
