package xyz.joaophp.carroswswork.service.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class CarrosDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "carros_wswork_database"

        fun build(application: Application): CarrosDatabase {
            val builder = Room.databaseBuilder(
                application,
                CarrosDatabase::class.java,
                DATABASE_NAME
            )
            return builder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
