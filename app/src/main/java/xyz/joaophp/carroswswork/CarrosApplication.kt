package xyz.joaophp.carroswswork

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import xyz.joaophp.carroswswork.di.databaseModule
import xyz.joaophp.carroswswork.di.networkModule

class CarrosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            allowOverride(true)
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@CarrosApplication)
            modules(modules)
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

    companion object {
        private val modules = listOf(
            databaseModule,
            networkModule
        )
    }
}