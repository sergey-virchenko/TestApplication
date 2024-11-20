package com.rounds.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import net.sqlcipher.database.SQLiteDatabase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class EncryptedDatabaseTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val databaseName = "app_database"
    private val correctPassword = "There is no spoon"

    @Before
    fun setup() {
        SQLiteDatabase.loadLibs(context)
    }

    @Test
    fun dbExists() {
        //Given
        val dbFile = File(context.getDatabasePath(databaseName).path)

        //Then
        assertTrue("Database file does not exist", dbFile.exists())
    }

    @Test
    fun GIVEN_db_is_encrypted_WHEN_use_right_password_THEN_db_is_open() {
        //Given
        val dbFile = File(context.getDatabasePath(databaseName).path)

        //When
        val db = SQLiteDatabase.openDatabase(
            dbFile.path,
            correctPassword,
            null,
            SQLiteDatabase.OPEN_READWRITE
        )

        //Then
        assertTrue("Database opened successfully with the correct password", db.isOpen)
        db.close()
    }

    @Test
    fun GIVEN_db_is_encrypted_WHEN_use_wrong_password_THEN_exception_is_thrown() {
        //Given
        val dbFile = File(context.getDatabasePath(databaseName).path)

        //When
        var exceptionThrown = false
        try {
            SQLiteDatabase.openDatabase(
                dbFile.path,
                "wrong_password",
                null,
                SQLiteDatabase.OPEN_READWRITE
            )
        } catch (e: Exception) {
            exceptionThrown = true
        }
        //Then
        assertTrue("Database should not open with incorrect password", exceptionThrown)
    }
}
