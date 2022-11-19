package xyz.joaophp.carroswswork.service.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.joaophp.carroswswork.data.Perfil
import xyz.joaophp.carroswswork.service.local.dao.PerfilDao
import xyz.joaophp.carroswswork.service.mappers.DBPerfilMapper.toPerfil
import xyz.joaophp.carroswswork.service.mappers.PerfilMapper.toEntity

class PerfilRepository(
    private val localDataSource: PerfilDao
) {

    suspend fun atualizar(perfil: Perfil) {
        localDataSource.insert(perfil.toEntity())
    }

    fun buscar(): Flow<Perfil?> {
        return localDataSource.get().map { entity ->
            entity?.toPerfil()
        }
    }
}