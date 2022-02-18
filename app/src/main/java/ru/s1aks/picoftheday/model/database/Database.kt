package ru.s1aks.picoftheday.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import ru.s1aks.picoftheday.ui.App

@androidx.room.Database(
    entities = [
        WorkNoteEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun workNotesDao(): WorkNotesDao

    companion object {
        private const val DB_NAME = "app_main.db"
        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}