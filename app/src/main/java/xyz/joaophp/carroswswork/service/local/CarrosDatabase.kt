package xyz.joaophp.carroswswork.service.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.joaophp.carroswswork.service.local.dao.CarroDao
import xyz.joaophp.carroswswork.service.local.data.DBCarro
import xyz.joaophp.carroswswork.service.local.data.DBLead

@Database(
    entities = [
        DBCarro::class,
        DBLead::class
    ],
    version = 1
)
abstract class CarrosDatabase : RoomDatabase() {
    abstract fun carroDao(): CarroDao

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
