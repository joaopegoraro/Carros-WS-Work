package xyz.joaophp.carroswswork.service.remote

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.joaophp.carroswswork.service.repositories.LeadRepository
import xyz.joaophp.carroswswork.utils.ApiResult

class PostLeadWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val leadRepository by inject<LeadRepository>()

    override suspend fun doWork(): Result {
        return when (leadRepository.enviarLeadsSalvas()) {
            is ApiResult.Success -> {
                Result.success()
            }
            else -> {
                Result.failure()
            }
        }
    }
}