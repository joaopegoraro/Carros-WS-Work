package xyz.joaophp.carroswswork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import xyz.joaophp.carroswswork.service.remote.PostLeadWorker
import xyz.joaophp.carroswswork.ui.navigation.MainNavigation
import xyz.joaophp.carroswswork.ui.theme.CarrosWSWorkTheme
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleWork()
        setContent()
    }

    private fun scheduleWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workBuilder = PeriodicWorkRequestBuilder<PostLeadWorker>(1, TimeUnit.HOURS)
        val workRequest = workBuilder
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            getString(R.string.post_leads_worker),
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    private fun setContent() {
        setContent {
            CarrosWSWorkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}
