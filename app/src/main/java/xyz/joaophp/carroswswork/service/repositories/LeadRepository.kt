package xyz.joaophp.carroswswork.service.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.service.local.dao.LeadDao
import xyz.joaophp.carroswswork.service.mappers.DBLeadMapper.toLeads
import xyz.joaophp.carroswswork.service.mappers.DBLeadMapper.toNetworkLeads
import xyz.joaophp.carroswswork.service.mappers.LeadMapper.toEntity
import xyz.joaophp.carroswswork.service.mappers.LeadMapper.toNetworkLead
import xyz.joaophp.carroswswork.service.remote.ApiService
import xyz.joaophp.carroswswork.service.remote.data.ReturnPost
import xyz.joaophp.carroswswork.utils.ApiResult

class LeadRepository(
    private val localDataSource: LeadDao,
    private val remoteDataSource: ApiService
) {

    fun listarLeads(): Flow<List<Lead>> {
        return localDataSource.getAll().map { entities ->
            entities.toLeads()
        }
    }

    suspend fun adicionar(lead: Lead) {
        localDataSource.insert(lead.toEntity())
    }

    suspend fun removerWithCarroId(carroId: Int) {
        localDataSource.deleteWithCarroId(carroId)
    }

    suspend fun enviarLead(lead: Lead) {
        val networkLead = lead.toNetworkLead()
        remoteDataSource.postLeads(listOf(networkLead))
    }

    suspend fun enviarLeadsSalvas(): ApiResult<ReturnPost?>? {
        val entities = localDataSource.getAll().firstOrNull() ?: emptyList()
        if (entities.isEmpty()) return null
        val networkLeads = entities.toNetworkLeads()
        return ApiResult.getResultFor { remoteDataSource.postLeads(networkLeads) }
    }
}